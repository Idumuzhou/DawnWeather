package com.dawn.weather.study.service

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.dawn.weather.R
import com.dawn.weather.ext.logD
import com.dawn.weather.ext.logE
import kotlin.concurrent.thread

class MyService : Service() {
    companion object{
        private const val TAG = "MyService"
    }

    private val downLoadBinder = DownLoaderBinder()

    class DownLoaderBinder : Binder(){
        fun startDownLoadBinder(){
            logE(TAG,"开始下载")
        }

        fun getProgress():Int{
            logE(TAG,"下载进度")
            return 0
        }
    }

    override fun onBind(intent: Intent): IBinder {
        logE(TAG,"onBind()")
        return downLoadBinder
    }

    override fun onCreate() {
        super.onCreate()
        logE(TAG,"onCreate()")

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel("my_service", "前台Service通知", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }
        val intent = Intent(this, ServiceActivity::class.java)
        val pi = PendingIntent.getActivity(this, 0, intent, 0)
        val notification = NotificationCompat.Builder(this, "my_service")
            .setContentTitle("This is content title")
            .setContentText("This is content text")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
            .setContentIntent(pi)
            .build()
        startForeground(1, notification)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        logE(TAG,"onStartCommand()")
        thread {
            //处理具体逻辑
            stopSelf()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        logE(TAG,"onDestroy()")
    }
}

class MyIntentService : IntentService("MyIntentService") {

    //在子线程中运行
    override fun onHandleIntent(intent: Intent?) {
        // 打印当前线程的id
        logD("MyIntentService", "Thread id is ${Thread.currentThread().name}")
    }

    override fun onDestroy() {
        super.onDestroy()
        logD("MyIntentService", "onDestroy executed")
    }

}