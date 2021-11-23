package com.dawn.weather.study.widget

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.dawn.weather.R
import com.dawn.weather.ext.toastShort

/**
 *  @author: LXL
 *  @description: 头部布局
 *  @date: 2021/11/11 10:29
 */
class TitleLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    private var title: String = "Title"
    private var isShowEditIcon = false

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.TitleLayout, 0, 0)
            .apply {
                title = getString(R.styleable.TitleLayout_title).toString()
                isShowEditIcon = getBoolean(R.styleable.TitleLayout_is_show_edit_icon, false)
            }

        val inflate = LayoutInflater.from(context).inflate(R.layout.title_layout, this)
        inflate.findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            val activity = context as Activity
            activity.finish()
        }
        inflate.findViewById<TextView>(R.id.tv_title).text = if (title == "null") "Title" else title
        inflate.findViewById<ImageView>(R.id.iv_edit).setOnClickListener {
            toastShort(context, "i'm Edit Imageview")
        }

        inflate.findViewById<ImageView>(R.id.iv_edit).visibility = if (isShowEditIcon) View.VISIBLE else View.GONE
    }

}