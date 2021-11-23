package com.dawn.weather.logic.network
import android.util.Log
import com.orhanobut.logger.Logger
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *  @author: LXL
 *  @description: Retrofit
 *  @date: 2021/11/23 11:08
 */
object ServiceCreator {
    private const val BASE_URL = "https://api.caiyunapp.com/"

    //配置OkHttp 添加日志拦截器
    private val okHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(HttpLoggingInterceptor { url, message ->
            Logger.t("OkHttp").d(message)
        }.setLevel(HttpLoggingInterceptor.Level.BODY))
    }.build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    fun <T> create(services: Class<T>): T = retrofit.create(services)

    //泛型实例化
    inline fun <reified T> create(): T = create(T::class.java)
}