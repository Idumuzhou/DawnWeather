package com.dawn.weather.study.data

import com.dawn.weather.study.data.BannerResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 *  @author: LXL
 *  @description: 接口服务
 *  @date: 2021/11/18 10:40
 */
interface AppService {

    @GET("banner/json")
    fun getBanner(): Call<BannerResponse>

}