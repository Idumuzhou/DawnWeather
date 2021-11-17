package com.dawn.weather.study.util

/**
 *  @author: LXL
 *  @description: description
 *  @date: 2021/11/17 16:32
 */
interface HttpCallbackListener {
    fun onFinish(response: String)
    fun onError(e: Exception)
}