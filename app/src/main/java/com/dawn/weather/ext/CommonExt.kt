package com.dawn.weather.ext

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import android.widget.Toast

/**
 *  @author: LXL
 *  @description: description
 *  @date: 2021/11/9 16:35
 */

fun toastShort(context: Context, msg: CharSequence) {
    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
}

fun toastLong(context: Context, msg: CharSequence) {
    Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
}

fun String.showToast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, duration).show()
}

fun Int.showToast(context: Context, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(context, this, duration).show()
}

fun logE( tag:String,msg : String){
    Log.e(tag,msg)
    Log.e("=","==================================")
}

fun logW( tag:String,msg : String){
    Log.w("=","==================================")
    Log.w(tag,msg)
}

fun logI( tag:String,msg : String){
    Log.i("=","==================================")
    Log.i(tag,msg)
}

fun logD( tag:String,msg : String){
    Log.d("=","==================================")
    Log.d(tag,msg)
}

/**
 * 判断当前系统是否是深色主题
 */
fun isDarkTheme(context: Context): Boolean {
    val flag = context.resources.configuration.uiMode and
            Configuration.UI_MODE_NIGHT_MASK
    return flag == Configuration.UI_MODE_NIGHT_YES
}