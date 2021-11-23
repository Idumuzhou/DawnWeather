package com.dawn.weather.study.network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.dawn.weather.R
import com.dawn.weather.study.data.AppService
import com.dawn.weather.study.data.BannerResponse
import com.dawn.weather.study.data.Constants
import com.dawn.weather.study.network.ServiceCreator.await
import com.google.gson.Gson
import com.orhanobut.logger.Logger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Retrofit
 */
class RetrofitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        /**
         * 获取Banner
         */
        findViewById<Button>(R.id.btn_get_banner).setOnClickListener {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.wanAndroidApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val apiServices = retrofit.create(AppService::class.java)
            apiServices.getBanner().enqueue(object : Callback<BannerResponse>{
                override fun onResponse(call: Call<BannerResponse>, response: Response<BannerResponse>) {
                    response.body()?.let {
                        Logger.e(Gson().toJson(it))
                    }
                }
                override fun onFailure(call: Call<BannerResponse>, t: Throwable) {
                    t.printStackTrace()
                }
            })

            //封装使用
            /*ServiceCreator.create(AppService::class.java).getBanner().enqueue(object : Callback<BannerResponse>{
                override fun onResponse(call: Call<BannerResponse>, response: Response<BannerResponse>) {

                }

                override fun onFailure(call: Call<BannerResponse>, t: Throwable) {

                }
            })*/
            //泛型实例化
            /*ServiceCreator.create<AppService>().getBanner().enqueue(object : Callback<BannerResponse>{
                override fun onResponse(call: Call<BannerResponse>, response: Response<BannerResponse>) {

                }

                override fun onFailure(call: Call<BannerResponse>, t: Throwable) {

                }
            })*/
        }

    }

    suspend fun getBannerData() {
        try {
            val response = ServiceCreator.create<AppService>().getBanner().await()
            // 对服务器响应的数据进行处理
        } catch (e: Exception) {
            // 对异常情况进行处理
        }
    }
}