package com.dawn.weather.ext

import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 *  @author: LXL
 *  @description: description
 *  @date: 2021/11/22 9:49
 */
fun View.showSnackBar(text: String, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, text, duration).show()
}

fun View.showSnackBar(resId: Int, duration: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, resId, duration).show()
}

/**
 * view.showSnackbar("This is Snackbar", "Action") {
    // 处理具体的逻辑
    }
 */
fun View.showSnackBar(text: String, actionText: String? = null,
                      duration: Int = Snackbar.LENGTH_SHORT, block: (() -> Unit)? = null) {
    val snackBar = Snackbar.make(this, text, duration)
    if (actionText != null && block != null) {
        snackBar.setAction(actionText) {
            block()
        }
    }
    snackBar.show()
}

fun View.showSnackBar(resId: Int, actionResId: Int? = null,
                      duration: Int = Snackbar.LENGTH_SHORT, block: (() -> Unit)? = null) {
    val snackBar = Snackbar.make(this, resId, duration)
    if (actionResId != null && block != null) {
        snackBar.setAction(actionResId) {
            block()
        }
    }
    snackBar.show()
}