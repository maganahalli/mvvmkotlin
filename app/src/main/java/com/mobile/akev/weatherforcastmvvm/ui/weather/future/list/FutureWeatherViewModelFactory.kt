package com.mobile.akev.weatherforcastmvvm.ui.weather.future.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mobile.akev.weatherforcastmvvm.data.provider.UnitProvider
import com.mobile.akev.weatherforcastmvvm.data.repository.ForcastRepository

@Suppress("UNCHECKED_CAST")
class FutureWeatherViewModelFactory(
    private val forcastRepository: ForcastRepository,
    private val unitProvider: UnitProvider
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FutureListWeatherViewModel(forcastRepository, unitProvider) as T
    }
}