package com.mobile.akev.weatherforcastmvvm.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mobile.akev.weatherforcastmvvm.data.db.entity.FutureWeatherEntry
import com.mobile.akev.weatherforcastmvvm.data.db.unitlocalized.future.detail.ImperialFutureDetailWeather
import com.mobile.akev.weatherforcastmvvm.data.db.unitlocalized.future.detail.MetricFutureDetailWeather
import com.mobile.akev.weatherforcastmvvm.data.db.unitlocalized.future.list.ImperialFutureWeather
import com.mobile.akev.weatherforcastmvvm.data.db.unitlocalized.future.list.MetricFutureWeather
import org.threeten.bp.LocalDate

@Dao
interface FutureWeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(futureWeatherEntries: List<FutureWeatherEntry>)

    @Query(value = "select * from FUTURE_WEATHER where date(date) >= date(:startDate)")
    fun getFutureWeatherMetric(startDate: LocalDate): LiveData<List<MetricFutureWeather>>

    @Query(value = "select * from FUTURE_WEATHER where date(date) = date(:date)")
    fun getFutureDetailWeatherMetric(date: LocalDate): LiveData<MetricFutureDetailWeather>

    @Query(value = "select * from FUTURE_WEATHER where date(date) >= date(:startDate)")
    fun getFutureWeatherImperial(startDate: LocalDate): LiveData<List<ImperialFutureWeather>>

    @Query(value = "select * from FUTURE_WEATHER where date(date) = date(:date)")
    fun getFutureDetailWeatherImperial(
        date: LocalDate
    ): LiveData<ImperialFutureDetailWeather>

    @Query(value = "select count(id)  from FUTURE_WEATHER where date(date) >= date(:startDate)")
    fun countFutureWeather(startDate: LocalDate): Int

    @Query(value = "delete from FUTURE_WEATHER where date(date) < date(:firstDateToKeep)")
    fun deleteOldEntries(firstDateToKeep: LocalDate)
}