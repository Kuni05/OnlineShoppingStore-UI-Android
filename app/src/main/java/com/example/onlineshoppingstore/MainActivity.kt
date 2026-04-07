package com.example.onlineshoppingstore

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val clothes = listOf(
            ClothingItem("简约卫衣", "399元", R.drawable.cloth_01),
            ClothingItem("时尚套装", "699元", R.drawable.cloth_02),
            ClothingItem("夏日连衣裙", "529元", R.drawable.cloth_03),
            ClothingItem("纯棉T恤", "199元", R.drawable.cloth_04),
            ClothingItem("牛仔外套", "459元", R.drawable.cloth_05),
            ClothingItem("休闲衬衫", "239元", R.drawable.cloth_06),
            ClothingItem("阔腿长裤", "329元", R.drawable.cloth_07),
            ClothingItem("针织开衫", "289元", R.drawable.cloth_08),
            ClothingItem("运动套装", "499元", R.drawable.cloth_09),
            ClothingItem("风衣外套", "799元", R.drawable.cloth_10)
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerClothes)
        val searchInput = findViewById<EditText>(R.id.etSearch)
        val clothingAdapter = ClothingAdapter(clothes) { item, position ->
            if (position < 3) {
                startActivity(
                    Intent(this, DetailActivity::class.java).apply {
                        putExtra(DetailActivity.EXTRA_NAME, item.name)
                        putExtra(DetailActivity.EXTRA_PRICE, item.price)
                        putExtra(DetailActivity.EXTRA_IMAGE, item.imageResId)
                    }
                )
            }
        }

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = clothingAdapter
        val spacing = (8 * resources.displayMetrics.density).toInt()
        recyclerView.addItemDecoration(GridSpacingItemDecoration(2, spacing, true))

        searchInput.doAfterTextChanged { editable ->
            clothingAdapter.updateQuery(editable?.toString().orEmpty())
        }
    }
}

class GridSpacingItemDecoration(
    private val spanCount: Int,
    private val spacing: Int,
    private val includeEdge: Boolean
) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: android.graphics.Rect,
        view: android.view.View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        if (includeEdge) {
            outRect.left = spacing - column * spacing / spanCount
            outRect.right = (column + 1) * spacing / spanCount

            if (position < spanCount) {
                outRect.top = spacing
            }
            outRect.bottom = spacing
        } else {
            outRect.left = column * spacing / spanCount
            outRect.right = spacing - (column + 1) * spacing / spanCount
            if (position >= spanCount) {
                outRect.top = spacing
            }
        }
    }
}