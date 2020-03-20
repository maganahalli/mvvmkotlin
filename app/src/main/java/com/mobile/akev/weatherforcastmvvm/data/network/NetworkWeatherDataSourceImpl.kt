package com.mobile.akev.weatherforcastmvvm.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mobile.akev.weatherforcastmvvm.data.network.response.CurrentWeatherResponse
import com.mobile.akev.weatherforcastmvvm.data.network.response.FutureWeatherResponse
import com.mobile.akev.weatherforcastmvvm.internal.NoConnectivityException

class NetworkWeatherDataSourceImpl(val weatherApiService: WeatherApiService) :
    NetworkWeatherDataSource {

    private val _downloadedCurrentWeather = MutableLiveData<CurrentWeatherResponse>()
    override val downloadedCurrentWeather: LiveData<CurrentWeatherResponse>
        get() = _downloadedCurrentWeather

    private val _downloadedFutureWeather = MutableLiveData<FutureWeatherResponse>()
    override val downloadedFutureWeather: LiveData<FutureWeatherResponse>
        get() = _downloadedFutureWeather

    override suspend fun fetchCurrentWeather(location: String, languageCode: String) {
        try {
            val fetchCurrentWeather =
                weatherApiService.getCurrentWeather(location, languageCode).await()
            _downloadedCurrentWeather.postValue(fetchCurrentWeather)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No Internet Connection", e)
        }
    }

    override suspend fun fetchFutureWeather(location: String, days: Int, languageCode: String) {
        try {
            val fetchFutureWeather =
                weatherApiService.getFutureWeather(location, days,languageCode).await()
            _downloadedFutureWeather.postValue(fetchFutureWeather)
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No Internet Connection", e)
        }
    }
}