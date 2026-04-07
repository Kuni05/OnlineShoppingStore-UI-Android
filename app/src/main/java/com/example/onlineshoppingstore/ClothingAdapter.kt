package com.example.onlineshoppingstore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ClothingAdapter(
    private val allItems: List<ClothingItem>,
    private val onItemClick: (ClothingItem, Int) -> Unit
) : RecyclerView.Adapter<ClothingAdapter.ClothingViewHolder>() {

    private val filteredItems = allItems.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClothingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_clothing, parent, false)
        return ClothingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ClothingViewHolder, position: Int) {
        val item = filteredItems[position]
        holder.bind(item)

        val sourceIndex = allItems.indexOf(item)
        holder.itemView.alpha = 1f
        holder.itemView.setOnClickListener { onItemClick(item, sourceIndex) }
    }

    override fun getItemCount(): Int = filteredItems.size

    fun updateQuery(query: String) {
        val keyword = query.trim()
        filteredItems.clear()

        if (keyword.isEmpty()) {
            filteredItems.addAll(allItems)
        } else {
            filteredItems.addAll(
                allItems.filter { item ->
                    item.name.contains(keyword, ignoreCase = true) ||
                        item.price.contains(keyword, ignoreCase = true)
                }
            )
        }
        notifyDataSetChanged()
    }

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

