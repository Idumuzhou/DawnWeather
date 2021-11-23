package com.dawn.weather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger


/**
 *  @author: LXL
 *  @description: DawnApplication
 *  @date: 2021/11/17 15:22
 */
class DawnApplication : Application(){
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context  //单独使用会造成内存泄漏

        const val TOKEN = "auv03EiB3Zt9RZcn"

    }
    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        Logger.addLogAdapter(AndroidLogAdapter())
    }
}