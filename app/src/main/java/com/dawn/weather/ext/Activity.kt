package com.dawn.weather.ext

import android.content.Context
import android.content.Intent

/**
 *  @author: LXL
 *  @description: description
 *  @date: 2021/11/17 14:13
 */


/**
 * 封装:
 * val intent = Intent(context, TestActivity::class.java)
 * context.startActivity(intent)
 *
 * 使用内联函数实现启动Activity功能
 * 使用 startActivity<TestActivity>(context)
 */
inline fun <reified T> startActivity(context: Context) {
    val intent = Intent(context, T::class.java)
    context.startActivity(intent)
}

/**
 * 携带参数 封装:
 * val intent = Intent(context, TestActivity::class.java)
 * intent.putExtra("param1", "data")
 * intent.putExtra("param2", 123)
 * context.startActivity(intent)
 *
 * 使用:
 * startActivity<TestActivity>(context) {
 *      putExtra("param1", "data")
 *      putExtra("param2", 123)
 * }
 **/
inline fun <reified T> startActivity(context: Context, block: Intent.() -> Unit) {
    val intent = Intent(context, T::class.java)
    intent.block()
    context.startActivity(intent)
}