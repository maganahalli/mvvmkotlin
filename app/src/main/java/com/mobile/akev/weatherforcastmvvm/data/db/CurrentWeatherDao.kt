package com.mobile.akev.weatherforcastmvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobile.akev.weatherforcastmvvm.data.db.entity.CURRENT_WEATHER_ID
import com.mobile.akev.weatherforcastmvvm.data.db.entity.Current
import com.mobile.akev.weatherforcastmvvm.data.db.unitlocalized.current.ImperialCurrentWeather
import com.mobile.akev.weatherforcastmvvm.data.db.unitlocalized.current.MetricCurrentWeather

@Dao
interface CurrentWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun updateInsert(weatherEntry: Current)

    @Query(value = "select * from CURRENT_WEATHER where id = $CURRENT_WEATHER_ID")
    fun getWeatherMetric(): LiveData<MetricCurrentWeather>

    @Query(value = "select * from CURRENT_WEATHER where id = $CURRENT_WEATHER_ID")
    fun getWeatherImperial(): LiveData<ImperialCurrentWeather>
}