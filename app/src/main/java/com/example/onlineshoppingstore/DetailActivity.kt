package com.example.onlineshoppingstore

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity

class DetailActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val name = intent.getStringExtra(EXTRA_NAME).orEmpty()
        val price = intent.getStringExtra(EXTRA_PRICE).orEmpty()
        val imageRes = intent.getIntExtra(EXTRA_IMAGE, android.R.drawable.ic_menu_gallery)
        val description = intent.getStringExtra(EXTRA_DESCRIPTION)
            ?.takeIf { it.isNotBlank() }
            ?: getString(R.string.detail_desc_fallback)

        findViewById<ImageView>(R.id.ivDetailImage).setImageResource(imageRes)
        findViewById<TextView>(R.id.tvDetailName).text = name
        findViewById<TextView>(R.id.tvDetailPrice).text = price
        findViewById<TextView>(R.id.tvDetailDescription).text = description
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_PRICE = "extra_price"
        const val EXTRA_IMAGE = "extra_image"
        const val EXTRA_DESCRIPTION = "extra_description"
    }
}

