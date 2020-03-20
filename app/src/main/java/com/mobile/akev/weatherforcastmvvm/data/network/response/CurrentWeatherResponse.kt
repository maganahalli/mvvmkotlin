package com.mobile.akev.weatherforcastmvvm.data.network.response

import com.mobile.akev.weatherforcastmvvm.data.db.entity.Current
import com.mobile.akev.weatherforcastmvvm.data.db.entity.WeatherLocation


data class CurrentWeatherResponse(
    val current: Current,
    val location: WeatherLocation
)