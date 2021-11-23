package com.dawn.weather.logic.network

import com.dawn.weather.DawnApplication
import com.dawn.weather.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


/**
 *  @author: LXL
 *  @description: PlaceService
 *  @date: 2021/11/23 11:05
 */
interface PlaceService {

    @GET("v2/place?token=${DawnApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>


}