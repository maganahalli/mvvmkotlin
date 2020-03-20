package com.mobile.akev.weatherforcastmvvm.ui.weather.future.details

import com.mobile.akev.weatherforcastmvvm.data.provider.UnitProvider
import com.mobile.akev.weatherforcastmvvm.data.repository.ForcastRepository
import com.mobile.akev.weatherforcastmvvm.internal.lazyDeferred
import com.mobile.akev.weatherforcastmvvm.ui.base.WeatherViewModel
import org.threeten.bp.LocalDate

class FutureDetailsViewModel (
    private val detailDate:LocalDate,
    private val forcastRepository: ForcastRepository,
    unitProvider: UnitProvider
): WeatherViewModel(forcastRepository,unitProvider) {
    val detailWeather by lazyDeferred {
        forcastRepository.getFutureWeatherByDate(detailDate,super.isMetricUnit)
    }
}
