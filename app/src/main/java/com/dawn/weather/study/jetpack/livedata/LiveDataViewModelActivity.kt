package com.dawn.weather.study.jetpack.livedata

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dawn.weather.R

/**
 * LiveDataViewModel
 */
class LiveDataViewModelActivity : AppCompatActivity() {
    private lateinit var viewModel: MyLiveDataViewModel
    lateinit var infoText: TextView
    lateinit var sp: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_model_live_data)
        infoText = findViewById(R.id.infoText_liveData)

        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt("count_reserved", 0)

        //获取实例
        //ViewModelProvider(<你的Activity或Fragment实例>).get(<你的ViewModel>::class.java)
        viewModel =
            ViewModelProvider(this, MyLiveDataViewModelFactory(countReserved)).get(MyLiveDataViewModel::class.java)

        findViewById<Button>(R.id.plusOneBtn_liveData).setOnClickListener {
            viewModel.plusOne()
        }

        findViewById<Button>(R.id.clearBtn_liveData).setOnClickListener {
            viewModel.clear()
        }

        viewModel.counter.observe(this, { count ->
            infoText.text = "$count"
        })

        findViewById<Button>(R.id.getUserBtn).setOnClickListener {
            val userId = (0..10000).random().toString()
            viewModel.getUser(userId)
        }

        viewModel.user.observe(this, Observer { user ->
            infoText.text = user.firstName
        })
    }

    override fun onPause() {
        super.onPause()
        sp.edit {
            putInt("count_reserved", viewModel.counter.value ?: 0)
        }
    }
}