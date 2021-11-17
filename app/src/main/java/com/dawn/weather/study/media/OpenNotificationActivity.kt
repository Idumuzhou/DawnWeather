package com.dawn.weather.study.media

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dawn.weather.R

class OpenNotificationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_notification)
        title = "Notification 打开页面"
    }
}