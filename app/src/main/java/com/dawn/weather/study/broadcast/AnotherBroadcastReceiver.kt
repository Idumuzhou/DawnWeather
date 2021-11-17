package com.dawn.weather.study.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.dawn.weather.ext.toastShort

/**
 * 有序广播
 */
class AnotherBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        toastShort(context,"有序广播")
    }
}