package com.dawn.weather.study.jetpack.livedata

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 *  @author: LXL
 *  @description: 缓存数据
 *  @date: 2021/11/22 10:27
 */
class MyLiveDataViewModelFactory(private val countReserved: Int):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyLiveDataViewModel(countReserved) as T
    }
}