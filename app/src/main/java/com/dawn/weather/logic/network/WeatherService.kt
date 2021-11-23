package com.dawn.weather.logic.network

import com.dawn.weather.DawnApplication
import com.dawn.weather.logic.model.DailyResponse
import com.dawn.weather.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *  @author: LXL
 *  @description: 天气信息
 *  @date: 2021/11/23 16:02
 */
interface WeatherService {
    @GET("v2.5/${DawnApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<RealtimeResponse>

    @GET("v2.5/${DawnApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String, @Path("lat") lat: String): Call<DailyResponse>

}