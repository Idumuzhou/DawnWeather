package com.dawn.weather.study.jetpack.viewmodel

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.edit
import androidx.lifecycle.ViewModelProvider
import com.dawn.weather.R

/**
 * ViewModel
 */
class ViewModelActivity : AppCompatActivity() {
    private lateinit var viewModel: MyViewModel
    lateinit var infoText: TextView
    lateinit var sp: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model)
        infoText = findViewById(R.id.infoText)

        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved", 0)

        //获取实例
        //ViewModelProvider(<你的Activity或Fragment实例>).get(<你的ViewModel>::class.java)
        viewModel = ViewModelProvider(this, MyViewModelFactory(countReserved)).get(MyViewModel::class.java)

        findViewById<Button>(R.id.plusOneBtn).setOnClickListener {
            viewModel.counter++
            infoText.text = "${viewModel.counter}"
        }
        infoText.text = "${viewModel.counter}"


        findViewById<Button>(R.id.clearBtn).setOnClickListener {
            infoText.text = "0"
        }
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved",viewModel.counter)
        }
    }
}