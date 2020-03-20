package com.mobile.akev.weatherforcastmvvm.data.provider

import com.mobile.akev.weatherforcastmvvm.data.db.entity.WeatherLocation

interface LocationProvider {
    suspend fun hasLocationChanged(lastWeatherLocation: WeatherLocation): Boolean
    suspend fun getPreferredLocationString(): String
}