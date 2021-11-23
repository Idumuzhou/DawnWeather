package com.dawn.weather.study.jetpack.livedata

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dawn.weather.study.data.User

/**
 *  @author: LXL
 *  @description: Repository类
 *  @date: 2021/11/22 13:07
 */
object Repository {
    fun getUser(userId: String): LiveData<User> {
        val userLiveData = MutableLiveData<User>()
        userLiveData.value = User(userId, userId, 0)
        return userLiveData
    }
}