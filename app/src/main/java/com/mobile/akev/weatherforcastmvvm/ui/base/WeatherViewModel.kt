package com.mobile.akev.weatherforcastmvvm.ui.base

import androidx.lifecycle.ViewModel
import com.mobile.akev.weatherforcastmvvm.data.provider.UnitProvider
import com.mobile.akev.weatherforcastmvvm.data.repository.ForcastRepository
import com.mobile.akev.weatherforcastmvvm.internal.UnitSystem
import com.mobile.akev.weatherforcastmvvm.internal.lazyDeferred

abstract  class WeatherViewModel(
    private val forcastRepository: ForcastRepository,
    unitProvider: UnitProvider
) : ViewModel() {
    private val unitSystem = unitProvider.getUnitSystem()
    val isMetricUnit: Boolean
        get() = unitSystem == UnitSystem.METRIC

    val weatherLocation by lazyDeferred {
        forcastRepository.getWeatherLocation()
    }
}