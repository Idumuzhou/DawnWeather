package com.dawn.weather.study.service

import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import androidx.loader.content.AsyncTaskLoader
import com.dawn.weather.R
import com.dawn.weather.study.data.News
import java.util.*
import kotlin.concurrent.thread

/**
 * Thread  Handler
 */
class ThreadActivity : AppCompatActivity() {
    private lateinit var tvChangeContent: TextView
    private val updateText = 1
    private val handler = object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when(msg.what){
                updateText -> tvChangeContent.text = "Change Hello World"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)
        title = "Thread  Handler"

        tvChangeContent = findViewById<TextView>(R.id.tv_change_content)
        findViewById<Button>(R.id.btn_change_text).setOnClickListener {
            thread {
                val msg = Message()
                msg.what = updateText
                msg.arg1 = 123
                msg.arg2 = 456
                msg.obj = News("新闻标题","新闻内容")
                handler.sendMessage(msg)
            }
        }
    }
}