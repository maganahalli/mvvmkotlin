package com.mobile.akev.weatherforcastmvvm.data.db.entity


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity(tableName = "FUTURE_WEATHER",indices = [Index(value = ["date"],unique = true)])
data class FutureWeatherEntry(
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null,
    val date: String,
    @Embedded
    val day: Day
)