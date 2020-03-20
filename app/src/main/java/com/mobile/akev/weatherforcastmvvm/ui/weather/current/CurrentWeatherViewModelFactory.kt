package com.mobile.akev.weatherforcastmvvm.ui.weather.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mobile.akev.weatherforcastmvvm.data.provider.UnitProvider
import com.mobile.akev.weatherforcastmvvm.data.repository.ForcastRepository

@Suppress("UNCHECKED_CAST")
class CurrentWeatherViewModelFactory(
    private val forcastRepository: ForcastRepository,
    private val unitProvider: UnitProvider
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CurrentWeatherViewModel(forcastRepository, unitProvider) as T
    }
}