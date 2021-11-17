package com.dawn.weather.study.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dawn.weather.ext.logD

/**
 *  @author: LXL
 *  @description: description
 *  @date: 2021/11/10 16:31
 */
open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //打印当前Activity名
        logD("BaseActivity",javaClass.simpleName)
        ActivityCollector.addActivity(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }

}