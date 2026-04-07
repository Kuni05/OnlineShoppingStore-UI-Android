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
            ClothingItem("简约卫衣", "399元", R.drawable.cloth_01, "宽松版型搭配柔软面料，日常通勤和周末出行都舒适百搭。"),
            ClothingItem("时尚套装", "699元", R.drawable.cloth_02, "上衣与下装同色系设计，省心搭配，一套穿出利落高级感。"),
            ClothingItem("夏日连衣裙", "529元", R.drawable.cloth_03, "轻盈垂坠面料，收腰剪裁修饰身形，适合约会与度假场景。"),
            ClothingItem("纯棉T恤", "199元", R.drawable.cloth_04, "100%纯棉亲肤透气，基础圆领版型，四季都能轻松搭配。"),
            ClothingItem("牛仔外套", "459元", R.drawable.cloth_05, "经典水洗牛仔，挺括有型，叠穿卫衣和连衣裙都很有层次。"),
            ClothingItem("休闲衬衫", "239元", R.drawable.cloth_06, "微宽松剪裁配合细腻纹理，通勤与休闲两种风格自由切换。"),
            ClothingItem("阔腿长裤", "329元", R.drawable.cloth_07, "高腰线设计拉长比例，垂顺裤型修饰腿部线条，舒适不紧绷。"),
            ClothingItem("针织开衫", "289元", R.drawable.cloth_08, "细针织工艺手感柔软，单穿或外搭都能营造温柔氛围。"),
            ClothingItem("运动套装", "499元", R.drawable.cloth_09, "速干面料结合弹力设计，兼顾运动机能与城市休闲感。"),
            ClothingItem("风衣外套", "799元", R.drawable.cloth_10, "经典中长款风衣，防风面料更实穿，轻松打造干练气质。")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerClothes)
        val searchInput = findViewById<EditText>(R.id.etSearch)
        val clothingAdapter = ClothingAdapter(clothes) { item, _ ->
            startActivity(
                Intent(this, DetailActivity::class.java).apply {
                    putExtra(DetailActivity.EXTRA_NAME, item.name)
                    putExtra(DetailActivity.EXTRA_PRICE, item.price)
                    putExtra(DetailActivity.EXTRA_IMAGE, item.imageResId)
                    putExtra(DetailActivity.EXTRA_DESCRIPTION, item.description)
                }
            )
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