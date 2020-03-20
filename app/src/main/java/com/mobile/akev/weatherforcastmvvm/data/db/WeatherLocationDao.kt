package com.mobile.akev.weatherforcastmvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobile.akev.weatherforcastmvvm.data.db.entity.WEATHER_LOCATION_ID
import com.mobile.akev.weatherforcastmvvm.data.db.entity.WeatherLocation

@Dao
interface WeatherLocationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateInsert(weatherLocation: WeatherLocation)

    @Query(value = "select * from WEATHER_LOCATION where id = $WEATHER_LOCATION_ID")
    fun getWeatherLocation(): LiveData<WeatherLocation>

    @Query(value = "select * from WEATHER_LOCATION where id = $WEATHER_LOCATION_ID")
    fun fetchWeatherLocation(): WeatherLocation
}