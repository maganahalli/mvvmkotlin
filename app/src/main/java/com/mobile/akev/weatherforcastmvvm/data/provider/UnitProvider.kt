package com.mobile.akev.weatherforcastmvvm.data.provider

import com.mobile.akev.weatherforcastmvvm.internal.UnitSystem

interface UnitProvider {
    fun getUnitSystem(): UnitSystem
}