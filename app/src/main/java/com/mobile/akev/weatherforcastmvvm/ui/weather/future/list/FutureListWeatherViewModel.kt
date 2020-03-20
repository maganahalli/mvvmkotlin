package com.mobile.akev.weatherforcastmvvm.ui.weather.future.list

import androidx.lifecycle.ViewModel
import com.mobile.akev.weatherforcastmvvm.data.provider.UnitProvider
import com.mobile.akev.weatherforcastmvvm.data.repository.ForcastRepository
import com.mobile.akev.weatherforcastmvvm.internal.lazyDeferred
import com.mobile.akev.weatherforcastmvvm.ui.base.WeatherViewModel
import org.threeten.bp.LocalDate

class FutureListWeatherViewModel (
    private val forcastRepository: ForcastRepository,
    unitProvider: UnitProvider
): WeatherViewModel(forcastRepository,unitProvider) {
    val weatherEntries by lazyDeferred {
        forcastRepository.getFutureWeatherList(LocalDate.now(),super.isMetricUnit,7)
    }
}
