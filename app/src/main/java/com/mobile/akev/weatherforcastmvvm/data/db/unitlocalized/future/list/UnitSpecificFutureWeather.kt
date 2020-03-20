package com.mobile.akev.weatherforcastmvvm.data.db.unitlocalized.future.list

import org.threeten.bp.LocalDate


interface UnitSpecificFutureWeather {
    val date: LocalDate
    val avgTemperature: Double
    val conditionText: String
    val conditionIconUrl: String
}