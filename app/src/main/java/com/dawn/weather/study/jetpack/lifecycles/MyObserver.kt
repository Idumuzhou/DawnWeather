package com.dawn.weather.study.jetpack.lifecycles

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.orhanobut.logger.Logger

/**
 *  @author: LXL
 *  @description: Lifecycles
 *  @date: 2021/11/22 10:46
 *  不过目前MyObserver虽然能够感知到Activity的生命周期发生了变化，
 *  却没有办法主动获知当前的生命周期状态。要解决这个问题也不难，
 *  只需要在MyObserver的构造函数中将Lifecycle对象传进来即可
 */
class MyObserver(val lifecycle:Lifecycle) : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() {
        Logger.e("Lifecycles onStart()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() {
        Logger.e("Lifecycles onStop()")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() {
        Logger.e("Lifecycles onPause()")
    }
}