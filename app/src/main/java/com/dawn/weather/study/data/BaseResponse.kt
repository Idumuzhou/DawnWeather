package com.dawn.weather.study.data


/**
 *  @author: LXL
 *  @description: description
 *  @date: 2021/11/18 12:56
 */
 class BaseResponse<T>(
    val data: T,
    val errorCode: Int,
    val errorMsg: String
)