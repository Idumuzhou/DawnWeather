package com.dawn.weather.study.design

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.dawn.weather.R
import com.google.android.material.appbar.CollapsingToolbarLayout

/**
 * 水果详情
 */
class FruitDetailActivity : AppCompatActivity() {
    companion object {
        const val FRUIT_NAME = "fruit_name"
        const val FRUIT_IMAGE = "fruit_image"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit_detail)

        val fruitName = intent.getStringExtra(FRUIT_NAME) ?: ""
        val fruitImage = intent.getStringExtra(FRUIT_IMAGE) ?: ""
        val findViewById = findViewById<Toolbar>(R.id.toolbar_fruit_detail)
        setSupportActionBar(findViewById)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar).title = fruitName
        Glide.with(this).load(fruitImage).into(findViewById(R.id.iv_fruit_detail))
        findViewById<TextView>(R.id.fruitContentText).text = generateFruitContent(fruitName)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun generateFruitContent(fruitName: String) = fruitName.repeat(500)

}