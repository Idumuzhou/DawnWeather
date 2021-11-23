package com.dawn.weather.logic

import androidx.lifecycle.liveData
import com.dawn.weather.logic.model.Weather
import com.dawn.weather.logic.network.DawnWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext

/**
 *  @author: LXL
 *  @description: 仓库类
 *  @date: 2021/11/23 13:44
 */
object Repository {

    fun searchPlaces(query: String) = fire(Dispatchers.IO) {
        val placeResponse = DawnWeatherNetwork.searchPlaces(query)
        if (placeResponse.status == "ok") {
            val places = placeResponse.places
            Result.success(places)
        } else {
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }
    }

    /**
     * 并发同步执行 获取实时天气和未来几天天气
     */
    fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO) {
        coroutineScope {
            val deferredRealtime = async {
                DawnWeatherNetwork.getRealtimeWeather(lng, lat)
            }
            val deferredDaily = async {
                DawnWeatherNetwork.getDailyWeather(lng, lat)
            }
            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()
            if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
                val weather = Weather(
                    realtimeResponse.result.realtime,
                    dailyResponse.result.daily
                )
                Result.success(weather)
            } else {
                Result.failure(
                    RuntimeException(
                        "realtime response status is ${realtimeResponse.status}" + "daily response status is ${dailyResponse.status}"
                    )
                )
            }
        }
    }

    /**
     * try catch
     */
    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }

}