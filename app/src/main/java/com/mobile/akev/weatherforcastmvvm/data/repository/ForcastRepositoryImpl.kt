package com.mobile.akev.weatherforcastmvvm.data.repository

import androidx.lifecycle.LiveData
import com.mobile.akev.weatherforcastmvvm.data.db.CurrentWeatherDao
import com.mobile.akev.weatherforcastmvvm.data.db.FutureWeatherDao
import com.mobile.akev.weatherforcastmvvm.data.db.WeatherLocationDao
import com.mobile.akev.weatherforcastmvvm.data.db.entity.WeatherLocation
import com.mobile.akev.weatherforcastmvvm.data.db.unitlocalized.current.UnitSpecificCurrentWeather
import com.mobile.akev.weatherforcastmvvm.data.db.unitlocalized.future.detail.UnitSpecificFutureDetailWeather
import com.mobile.akev.weatherforcastmvvm.data.db.unitlocalized.future.list.UnitSpecificFutureWeather
import com.mobile.akev.weatherforcastmvvm.data.network.NetworkWeatherDataSource
import com.mobile.akev.weatherforcastmvvm.data.network.response.CurrentWeatherResponse
import com.mobile.akev.weatherforcastmvvm.data.network.response.FutureWeatherResponse
import com.mobile.akev.weatherforcastmvvm.data.provider.LocationProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
import org.threeten.bp.ZonedDateTime
import java.util.*

class ForcastRepositoryImpl(
    private val currentWeatherDao: CurrentWeatherDao,
    private val futureWeatherDao: FutureWeatherDao,
    private val weatherLocationDao: WeatherLocationDao,
    private val weatherDataSource: NetworkWeatherDataSource,
    private val locationProvider: LocationProvider
) : ForcastRepository {

    init {
        weatherDataSource.apply {
            downloadedCurrentWeather.observeForever { newCurrentWeather ->
                // persist
                persistFetchedCurrentWeather(newCurrentWeather)
            }

            downloadedFutureWeather.observeForever { newFutureWeather ->
                persistFetchedFutureWeatherList(newFutureWeather)
            }
        }
    }

    override suspend fun getCurrentWeather(metric: Boolean): LiveData<out UnitSpecificCurrentWeather> {
        return withContext(Dispatchers.IO) {
            initWeatherData()
            return@withContext if (metric) currentWeatherDao.getWeatherMetric()
            else currentWeatherDao.getWeatherImperial()
        }
    }

    override suspend fun getFutureWeatherList(
        startDate: LocalDate,
        metric: Boolean,
        days: Int
    ): LiveData<out List<UnitSpecificFutureWeather>> {
        return withContext(Dispatchers.IO) {
            initWeatherData()
            return@withContext if (metric) futureWeatherDao.getFutureWeatherMetric(startDate)
            else futureWeatherDao.getFutureWeatherImperial(startDate)
        }
    }

    override suspend fun getFutureWeatherByDate(
        date: LocalDate,
        metric: Boolean
    ): LiveData<out UnitSpecificFutureDetailWeather> {
        initWeatherData()
        return withContext(Dispatchers.IO) {
            return@withContext if (metric) futureWeatherDao.getFutureDetailWeatherMetric(date)
            else futureWeatherDao.getFutureDetailWeatherImperial(date)
        }
    }

    override suspend fun getWeatherLocation(): LiveData<WeatherLocation> {
        return withContext(Dispatchers.IO) {
            return@withContext weatherLocationDao.getWeatherLocation()
        }
    }


    private fun persistFetchedCurrentWeather(fetchedWeather: CurrentWeatherResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            currentWeatherDao.updateInsert(fetchedWeather.current)
            weatherLocationDao.updateInsert(fetchedWeather.location)
        }
    }

    private fun persistFetchedFutureWeatherList(fetchedWeather: FutureWeatherResponse) {
        fun deleteOldForcastData() {
            val today = LocalDate.now()
            futureWeatherDao.deleteOldEntries(today)
        }

        GlobalScope.launch(Dispatchers.IO) {
            deleteOldForcastData()
            val futureWeatherList = fetchedWeather.futureWeatherEntries.entries
            futureWeatherDao.insert(futureWeatherList)
            weatherLocationDao.updateInsert(fetchedWeather.location)
        }
    }

    private suspend fun initWeatherData() {
        val lastWeatherLocation = weatherLocationDao.fetchWeatherLocation()

        if (lastWeatherLocation == null || locationProvider.hasLocationChanged(lastWeatherLocation)) {
            fetchCurrentWeather()
            fetchFutureWeather()
            return
        }
        if (isFetchCurrentNeeded(lastWeatherLocation.zoneDateTime))
            fetchCurrentWeather()

        if (isFetchFutureNeeded())
            fetchFutureWeather()

    }

    private fun isFetchCurrentNeeded(lastFetchedTime: ZonedDateTime): Boolean {
        val thirtyMinutesAgo = ZonedDateTime.now().minusMinutes(30)
        return lastFetchedTime.isBefore(thirtyMinutesAgo)
    }

    private fun isFetchFutureNeeded(): Boolean {
        val today = LocalDate.now()
        val futureWeatherCount = futureWeatherDao.countFutureWeather(today)
        return futureWeatherCount < 7
    }

    private suspend fun fetchCurrentWeather() {
        weatherDataSource.fetchCurrentWeather(
            locationProvider.getPreferredLocationString(),
            Locale.getDefault().language
        )
    }

    private suspend fun fetchFutureWeather() {
        weatherDataSource.fetchFutureWeather(
            locationProvider.getPreferredLocationString(), 7,
            Locale.getDefault().language
        )
    }
}