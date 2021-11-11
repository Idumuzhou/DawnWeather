package com.dawn.weathe.study.widget

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.dawn.weathe.R
import com.dawn.weathe.ext.toastShort

/**
 *  @author: LXL
 *  @description: 头部布局
 *  @date: 2021/11/11 10:29
 */
class TitleLayout(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    private var title = ""

    init {
        fun setTitle(title: String) {
            this.title = title
        }

        val inflate = LayoutInflater.from(context).inflate(R.layout.title_layout, this)
        inflate.findViewById<ImageView>(R.id.iv_back).setOnClickListener {
            val activity = context as Activity
            activity.finish()
        }
        inflate.findViewById<TextView>(R.id.tv_title).text = if (title.isNotEmpty()) title else "生命周期"
        inflate.findViewById<ImageView>(R.id.iv_edit).setOnClickListener {
            toastShort(context, "i'm Edit Imageview")
        }
    }

}