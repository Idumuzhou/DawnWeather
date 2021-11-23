package com.dawn.weather.study.jetpack.viewmodel

import androidx.lifecycle.ViewModel

/**
 *  @author: LXL
 *  @description: description
 *  @date: 2021/11/22 10:12
 *  原来我们一直使用的都是在Activity中手动获取ViewModel中的数据这种交互方式，但是ViewModel却无法将数据的变化主动通知给Activity
 *  ViewModel的生命周期是长于Activity的，如果把Activity的实例传给ViewModel，就很有可能会因为Activity无法释放而造成内存泄漏，这是一种非常错误的做法
 */
class MyViewModel(countReserved: Int) : ViewModel() {
    var counter = countReserved
}