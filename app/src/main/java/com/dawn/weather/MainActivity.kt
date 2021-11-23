package com.dawn.weather

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.dawn.weather.study.ui.KotlinStudyActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        /*
        *跳转到Kotlin Study 模块
        //val btnHello = findViewById<Button>(R.id.btn_hello)
        val btnHello: Button = findViewById(R.id.btn_hello)
        btnHello.setOnClickListener {
            startActivity(Intent(this, KotlinStudyActivity::class.java))
        }*/


    }
}