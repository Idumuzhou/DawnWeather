package com.dawn.weather.study.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.dawn.weather.R
import com.dawn.weather.ext.toastShort

/**
 * 广播
 */
class BroadcastReceiverActivity : AppCompatActivity() {

    private lateinit var timeChangeReceive: TimeChangeReceive

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broadcast_receiver)
        title = "广播BroadcastReceiver"
        //标准广播
        val intentFilter = IntentFilter()
        intentFilter.addAction("android.intent.action.TIME_TICK")
        timeChangeReceive = TimeChangeReceive()
        registerReceiver(timeChangeReceive, intentFilter)

        //发送广播(标准自定义广播)
        findViewById<Button>(R.id.button_my_broadcast).setOnClickListener {
            val intent = Intent("com.dawn.weather.study.broadcast.MyBroadcastReceiver")
            intent.setPackage(packageName)
            //sendBroadcast(intent)
            sendOrderedBroadcast(intent,null)  //广播拦截功能
        }

        //有序广播
        findViewById<Button>(R.id.button_orderly_broadcast).setOnClickListener {
            val intent = Intent("com.dawn.weather.study.broadcast.MyBroadcastReceiver")
            intent.setPackage(packageName)
            sendBroadcast(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(timeChangeReceive)
    }

    inner class TimeChangeReceive : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            context?.let { toastShort(it, "Time has changed") }
        }

    }
}