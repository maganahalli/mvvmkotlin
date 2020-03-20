package com.mobile.akev.weatherforcastmvvm.data.provider

import android.content.Context
import com.mobile.akev.weatherforcastmvvm.internal.UnitSystem

const val UNIT_SYSTEM = "UNIT_SYSTEM"

class UnitProviderImpl(context: Context) : PreferenceProvider(context), UnitProvider {
    override fun getUnitSystem(): UnitSystem {
        val selectedName = preference.getString(UNIT_SYSTEM, UnitSystem.METRIC.name)
        return UnitSystem.valueOf(selectedName!!)
    }
}