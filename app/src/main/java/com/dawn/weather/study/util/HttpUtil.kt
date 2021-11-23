package com.dawn.weather.study.util

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 *  @author: LXL
 *  @description: HttpUtil
 *  @date: 2021/11/17 16:29
 */
object HttpUtil {

    /**
     * 未使用线程,没有回调 只有返回值
     * 使用:
     * val address = "https://www.baidu.com"
     * val response = HttpUtil.sendHttpRequest(address)
     */
    fun sendHttpRequest(address: String): String {
        var connection: HttpURLConnection? = null
        try {
            val response = StringBuilder()
            val url = URL(address)
            connection = url.openConnection() as HttpURLConnection
            connection.connectTimeout = 8000
            connection.readTimeout = 8000
            val input = connection.inputStream
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    response.append(it)
                }
            }
            return response.toString()
        } catch (e: Exception) {
            e.printStackTrace()
            return e.message.toString()
        } finally {
            connection?.disconnect()
        }
    }

    /**
     * 线程+接口回调数据:
     * HttpUtil.sendHttpRequest(address, object : HttpCallbackListener {
    override fun onFinish(response: String) {
    // 得到服务器返回的具体内容
    }

    override fun onError(e: Exception) {
    // 在这里对异常情况进行处理
    }
    })
     */
    fun sendHttpRequest2(address: String, callBack: HttpCallbackListener) {
        thread {
            var connection: HttpURLConnection? = null
            try {
                val response = StringBuilder()
                val url = URL(address)
                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 8000
                connection.readTimeout = 8000
                val input = connection.inputStream
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                callBack.onFinish(response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
                callBack.onError(e)
            } finally {
                connection?.disconnect()
            }
        }
    }

    /**
     * 协程简化上面版本
     */
    suspend fun sendHttpRequest3(address: String): String {
        return suspendCoroutine { continuation ->
            HttpUtil.sendHttpRequest2(address, object : HttpCallbackListener {
                override fun onFinish(response: String) {
                    continuation.resume(response)
                }

                override fun onError(e: Exception) {
                    continuation.resumeWithException(e)
                }
            })
        }

    }

    /**
    HttpUtil.sendOkHttpRequest(address, object : Callback {
    override fun onResponse(call: Call, response: Response) {
    // 得到服务器返回的具体内容
    val responseData = response.body?.string()
    }

    override fun onFailure(call: Call, e: IOException) {
    // 在这里对异常情况进行处理
    }
    })
     */
    fun sendOkHttpRequest(address: String, callback: okhttp3.Callback) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(address)
            .build()
        client.newCall(request).enqueue(callback)
    }


}