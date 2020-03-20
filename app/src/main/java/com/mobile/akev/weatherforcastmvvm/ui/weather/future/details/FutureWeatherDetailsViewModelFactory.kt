package com.mobile.akev.weatherforcastmvvm.ui.weather.future.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mobile.akev.weatherforcastmvvm.data.provider.UnitProvider
import com.mobile.akev.weatherforcastmvvm.data.repository.ForcastRepository
import com.mobile.akev.weatherforcastmvvm.ui.weather.future.list.FutureListWeatherViewModel
import org.threeten.bp.LocalDate

class FutureWeatherDetailsViewModelFactory (
    private val detailDate:LocalDate,
    private val forcastRepository: ForcastRepository,
    private val unitProvider: UnitProvider
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FutureDetailsViewModel(detailDate,forcastRepository, unitProvider) as T
    }
}