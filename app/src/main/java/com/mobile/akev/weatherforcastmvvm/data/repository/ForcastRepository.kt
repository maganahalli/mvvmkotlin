package com.mobile.akev.weatherforcastmvvm.data.repository

import androidx.lifecycle.LiveData
import com.mobile.akev.weatherforcastmvvm.data.db.entity.WeatherLocation
import com.mobile.akev.weatherforcastmvvm.data.db.unitlocalized.current.UnitSpecificCurrentWeather
import com.mobile.akev.weatherforcastmvvm.data.db.unitlocalized.future.detail.UnitSpecificFutureDetailWeather
import com.mobile.akev.weatherforcastmvvm.data.db.unitlocalized.future.list.UnitSpecificFutureWeather
import org.threeten.bp.LocalDate

interface ForcastRepository {
    suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeather>
    suspend fun getFutureWeatherList(startDate:LocalDate,metric: Boolean,days:Int):LiveData<out  List<UnitSpecificFutureWeather>>
    suspend fun getFutureWeatherByDate(startDate:LocalDate,metric: Boolean):LiveData<out UnitSpecificFutureDetailWeather>
    suspend fun getWeatherLocation(): LiveData<WeatherLocation>
}