package com.dawn.weather

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger


/**
 *  @author: LXL
 *  @description: DawnApplication
 *  @date: 2021/11/17 15:22
 */
class DawnApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        Logger.addLogAdapter(AndroidLogAdapter())
    }
}