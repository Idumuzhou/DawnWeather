package com.dawn.weather.study.jetpack.lifecycles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dawn.weather.R
import com.orhanobut.logger.Logger

/**
 *  Lifecycles
 */
class LifecyclesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lifecycles)
        //添加观察者
        lifecycle.addObserver(MyObserver(lifecycle))
        //获取当前生命周期状态
        Logger.e(lifecycle.currentState.toString())
    }
}