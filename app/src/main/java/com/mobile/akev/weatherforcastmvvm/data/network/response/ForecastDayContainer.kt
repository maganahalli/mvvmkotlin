package com.mobile.akev.weatherforcastmvvm.data.network.response

import com.google.gson.annotations.SerializedName
import com.mobile.akev.weatherforcastmvvm.data.db.entity.FutureWeatherEntry


data class ForecastDayContainer(
    @SerializedName(value = "forecastday")
    val entries: List<FutureWeatherEntry>
)