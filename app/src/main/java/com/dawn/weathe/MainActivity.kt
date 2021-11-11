package com.dawn.weathe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.dawn.weathe.study.ui.KotlinStudyActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val btnHello = findViewById<Button>(R.id.btn_hello)
        val btnHello: Button = findViewById(R.id.btn_hello)
        btnHello.setOnClickListener {
            startActivity(Intent(this, KotlinStudyActivity::class.java))
        }
    }
}