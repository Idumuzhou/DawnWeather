package com.dawn.weather.study.service

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import com.dawn.weather.R
import com.dawn.weather.ext.logD

/**
 * Service
 */
class ServiceActivity : AppCompatActivity() {
    lateinit var downloadBinder: MyService.DownLoaderBinder

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            downloadBinder = service as MyService.DownLoaderBinder
            downloadBinder.startDownLoadBinder()
            downloadBinder.getProgress()
        }

        override fun onServiceDisconnected(name: ComponentName?) {

        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service)
        title = "Service"

        /**
         * 启动服务
         */
        findViewById<Button>(R.id.btn_start_service).setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            startService(intent)
        }

        /**
         * 停止服务
         */
        findViewById<Button>(R.id.btn_stop_service).setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            stopService(intent)
        }

        /**
         * 绑定服务
         */
        findViewById<Button>(R.id.btn_bindService).setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            bindService(intent, connection, Context.BIND_AUTO_CREATE)  //绑定service
        }

        /**
         * 取消绑定服务
         */
        findViewById<Button>(R.id.btn_unbindService).setOnClickListener {
            unbindService(connection)  //解绑service
        }

        /**
         * IntentService
         */
        findViewById<Button>(R.id.btn_startIntentService).setOnClickListener {
            // 打印主线程的id
            logD("ServiceActivity", "Thread id is ${Thread.currentThread().name}")
            val intent = Intent(this, MyIntentService::class.java)
            startService(intent)
        }
        }
}