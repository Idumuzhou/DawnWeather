package com.dawn.weather.study.jetpack.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 *  @author: LXL
 *  @description: 缓存数据
 *  @date: 2021/11/22 10:27
 */
class MyViewModelFactory(private val countReserved: Int):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MyViewModel(countReserved) as T
    }
}