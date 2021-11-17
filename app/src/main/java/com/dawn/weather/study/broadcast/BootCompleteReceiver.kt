package com.dawn.weather.study.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.dawn.weather.ext.toastShort

/**
 * 开机启动的功能
 */
class BootCompleteReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        toastShort(context,"Boot Complete")
    }
}