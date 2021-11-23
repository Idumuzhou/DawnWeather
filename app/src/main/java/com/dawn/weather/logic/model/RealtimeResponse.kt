package com.dawn.weather.logic.model

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 *  @author: LXL
 *  @description: 天气信息
 *  @date: 2021/11/23 15:56
 */

data class Weather(val realtime: RealtimeResponse.Realtime, val daily: DailyResponse.Daily)

//实时天气信息
data class RealtimeResponse(val status: String, val result: Result) {

    data class Result(val realtime: Realtime)

    data class Realtime(
        val skycon: String,
        val temperature: Float,
        @SerializedName("air_quality") val airQuality: AirQuality
    )

    data class AirQuality(val aqi: AQI)

    data class AQI(val chn: Float)

}

//未来几天天气信息
data class DailyResponse(val status: String, val result: Result) {

    data class Result(val daily: Daily)

    data class Daily(val temperature: List<Temperature>, val skycon: List<Skycon>,
                     @SerializedName("life_index") val lifeIndex: LifeIndex)

    data class Temperature(val max: Float, val min: Float)

    data class Skycon(val value: String, val date: Date)

    data class LifeIndex(val coldRisk: List<LifeDescription>, val carWashing:
    List<LifeDescription>, val ultraviolet: List<LifeDescription>,
                         val dressing: List<LifeDescription>)

    data class LifeDescription(val desc: String)

}