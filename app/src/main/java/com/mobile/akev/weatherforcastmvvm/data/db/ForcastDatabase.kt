package com.mobile.akev.weatherforcastmvvm.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mobile.akev.weatherforcastmvvm.data.db.entity.Current
import com.mobile.akev.weatherforcastmvvm.data.db.entity.FutureWeatherEntry
import com.mobile.akev.weatherforcastmvvm.data.db.entity.WeatherLocation

@Database(entities = [Current::class, FutureWeatherEntry::class,WeatherLocation::class], version = 1)
@TypeConverters(LocalDateConverter::class)
abstract class ForcastDatabase : RoomDatabase() {
    abstract fun currentWeatherDao(): CurrentWeatherDao
    abstract  fun futureWeatherDao():FutureWeatherDao
    abstract fun weatherLocationDao(): WeatherLocationDao

    companion object {
        @Volatile
        private var instance: ForcastDatabase? = null
        private val LOCK = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context): ForcastDatabase = Room.databaseBuilder(
            context.applicationContext,
            ForcastDatabase::class.java,
            "forcast.db"
        ).build()
    }
}