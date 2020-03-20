package com.mobile.akev.weatherforcastmvvm.data.network

import androidx.lifecycle.LiveData
import com.mobile.akev.weatherforcastmvvm.data.network.response.CurrentWeatherResponse
import com.mobile.akev.weatherforcastmvvm.data.network.response.FutureWeatherResponse

interface NetworkWeatherDataSource {
    val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
    val downloadedFutureWeather: LiveData<FutureWeatherResponse>
    suspend fun fetchCurrentWeather(location: String, languageCode: String)
    suspend fun fetchFutureWeather(location: String, days:Int,languageCode: String)
}