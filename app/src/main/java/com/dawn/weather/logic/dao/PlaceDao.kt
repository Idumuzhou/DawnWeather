package com.dawn.weather.logic.dao

import android.content.Context
import androidx.core.content.edit
import com.dawn.weather.DawnApplication
import com.dawn.weather.logic.model.Place
import com.google.gson.Gson

/**
 *  @author: LXL
 *  @description: description
 *  @date: 2021/11/24 9:39
 */
object PlaceDao {
    fun savePlace(place: Place) {
        sharedPreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    fun getSavedPlace(): Place {
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }


    fun isPlaceSaved() = sharedPreferences().contains("place")

    private fun sharedPreferences() = DawnApplication.context.
    getSharedPreferences("dawn_weather", Context.MODE_PRIVATE)


}