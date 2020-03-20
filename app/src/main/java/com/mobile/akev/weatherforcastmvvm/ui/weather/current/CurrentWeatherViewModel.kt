package com.mobile.akev.weatherforcastmvvm.ui.weather.current

import androidx.lifecycle.ViewModel
import com.mobile.akev.weatherforcastmvvm.data.provider.UnitProvider
import com.mobile.akev.weatherforcastmvvm.data.repository.ForcastRepository
import com.mobile.akev.weatherforcastmvvm.internal.UnitSystem
import com.mobile.akev.weatherforcastmvvm.internal.lazyDeferred
import com.mobile.akev.weatherforcastmvvm.ui.base.WeatherViewModel

class CurrentWeatherViewModel(
    private val forcastRepository: ForcastRepository,
    unitProvider: UnitProvider
) : WeatherViewModel(forcastRepository,unitProvider) {

    val weather by lazyDeferred {
        forcastRepository.getCurrentWeather(super.isMetricUnit)
    }

}
