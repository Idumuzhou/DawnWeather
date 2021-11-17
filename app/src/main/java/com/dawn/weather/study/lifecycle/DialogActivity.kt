package com.dawn.weather.study.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dawn.weather.R

class DialogActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog)
    }
}