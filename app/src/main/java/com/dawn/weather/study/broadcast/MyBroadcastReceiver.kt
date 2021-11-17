package com.dawn.weather.study.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.dawn.weather.ext.toastShort

/**
 * 自定义标准广播
 */
class MyBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        toastShort(context,"我是标准 自定义 广播")
        abortBroadcast()    //广播拦截
    }
}