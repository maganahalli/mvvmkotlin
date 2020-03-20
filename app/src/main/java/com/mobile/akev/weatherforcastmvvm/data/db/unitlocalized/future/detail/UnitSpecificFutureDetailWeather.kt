package com.mobile.akev.weatherforcastmvvm.data.db.unitlocalized.future.detail

import org.threeten.bp.LocalDate

interface UnitSpecificFutureDetailWeather {
    val date:LocalDate
    val maxTemperature: Double
    val minTemperature: Double
    val avgTemperature: Double
    val conditionText: String
    val conditionIconUrl: String
    val maxWindSpeed: Double
    val totalPrecipitation: Double
    val avgVisibilityDistance: Double
    val uv: Double
}