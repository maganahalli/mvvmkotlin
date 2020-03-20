package com.mobile.akev.weatherforcastmvvm.data.network.response

import com.google.gson.annotations.SerializedName
import com.mobile.akev.weatherforcastmvvm.data.db.entity.WeatherLocation


data class FutureWeatherResponse(
    @SerializedName(value = "forecast")
    val futureWeatherEntries: ForecastDayContainer,
    val location: WeatherLocation
)