package com.example.onlineshoppingstore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ClothingAdapter(
    private val items: List<ClothingItem>,
    private val onItemClick: (ClothingItem, Int) -> Unit
) : RecyclerView.Adapter<ClothingAdapter.ClothingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_clothing, parent, false)
        return ClothingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClothingViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)

        if (position < 3) {
            holder.itemView.alpha = 1f
            holder.itemView.setOnClickListener { onItemClick(item, position) }
        } else {
            holder.itemView.alpha = 0.96f
            holder.itemView.setOnClickListener(null)
        }
    }

    override fun getItemCount(): Int = items.size

    class ClothingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.findViewById(R.id.ivCloth)
        private val name: TextView = itemView.findViewById(R.id.tvName)
        private val price: TextView = itemView.findViewById(R.id.tvPrice)

        fun bind(item: ClothingItem) {
            image.setImageResource(item.imageResId)
            name.text = item.name
            price.text = item.price
        }
    }
}

