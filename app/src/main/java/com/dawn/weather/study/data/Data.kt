package com.dawn.weather.study.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 *  @author: LXL
 *  @description: description
 *  @date: 2021/11/18 12:52
 */

class BannerResponse(val data: List<Banner>, val errorCode: Int, val errorMsg: String)

class Banner(
    val desc: String,
    val id: Int,
    val imagePath: String,
    val isVisible: Int,
    val order: Int,
    val title: String,
    val type: Int,
    val url: String
)

@Entity
data class User(
    var firstName: String,
    var lastName: String,
    var age: Int
){
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}



