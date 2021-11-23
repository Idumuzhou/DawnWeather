package com.dawn.weather.study.network

import com.dawn.weather.study.data.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 *  @author: LXL
 *  @description: Retrofit 公共部分
 *  @date: 2021/11/18 13:35
 */
object ServiceCreator {

    private val retrofit = Retrofit.Builder()
        .baseUrl(Constants.wanAndroidApi)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun <T> create(services: Class<T>): T = retrofit.create(services)

    //泛型实例化
    inline fun <reified T> create(): T = create(T::class.java)

    /**
     * 协程简化
     */
    suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                        RuntimeException("response body is null"))
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }
    }
}