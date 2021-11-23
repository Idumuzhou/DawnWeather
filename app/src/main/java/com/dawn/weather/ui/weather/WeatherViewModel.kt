package com.dawn.weather.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dawn.weather.logic.Repository
import com.dawn.weather.logic.model.Location

/**
 *  @author: LXL
 *  @description: 获取实时天气和未来几天天气
 *  @date: 2021/11/23 16:15
 */
class WeatherViewModel : ViewModel() {
    private val locationLiveData = MutableLiveData<Location>()

    var locationLng = ""

    var locationLat = ""

    var placeName = ""

    val weatherLiveData = Transformations.switchMap(locationLiveData) { location ->
        Repository.refreshWeather(location.lng, location.lat)
    }

    fun refreshWeather(lng: String, lat: String) {
        locationLiveData.value = Location(lng, lat)
    }

}