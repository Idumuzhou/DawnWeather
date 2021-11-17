package com.dawn.weather.study.media

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat
import com.dawn.weather.R

/**
 * Notification
 */
class NotificationActivity : AppCompatActivity() {
    companion object {
        //const val MessageLevel = "normal"
        const val MessageLevel = "Important"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)
        title = "Notification"

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //notificationManager.cancel(1)


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //渠道ID、渠道名称、重要等级   这3个参数
            val channel = NotificationChannel(MessageLevel, MessageLevel, NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        //跳转意图
        val intent = Intent(this, OpenNotificationActivity::class.java)
        val pi = PendingIntent.getActivity(this, 0, intent, 0)

        val notificationCompat = NotificationCompat.Builder(this, MessageLevel).run {
            setContentTitle("This is content title")
            setContentText("This is content text")
            /*setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("This is content text This is content text This is content text This is content text This is content text This is content text")
            ) */ //长内容显示
            /*setStyle(
                NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(resources, R.mipmap.dawn))
            )*/
            setSmallIcon(R.mipmap.ic_launcher)
            setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
            setContentIntent(pi) //点击跳转意图
            setAutoCancel(true)  //跳转完之后通知消息消失
            build()
        }

        /**
         * 发送通知
         */
        findViewById<Button>(R.id.btn_send_notice).setOnClickListener {
            notificationManager.notify(1, notificationCompat)
        }
    }
}