package com.mobile.akev.weatherforcastmvvm

import android.app.Application
import android.content.Context
import androidx.preference.PreferenceManager
import com.google.android.gms.location.LocationServices
import com.jakewharton.threetenabp.AndroidThreeTen
import com.mobile.akev.weatherforcastmvvm.data.db.ForcastDatabase
import com.mobile.akev.weatherforcastmvvm.data.network.*
import com.mobile.akev.weatherforcastmvvm.data.provider.LocationProvider
import com.mobile.akev.weatherforcastmvvm.data.provider.LocationProviderImpl
import com.mobile.akev.weatherforcastmvvm.data.provider.UnitProvider
import com.mobile.akev.weatherforcastmvvm.data.provider.UnitProviderImpl
import com.mobile.akev.weatherforcastmvvm.data.repository.ForcastRepository
import com.mobile.akev.weatherforcastmvvm.data.repository.ForcastRepositoryImpl
import com.mobile.akev.weatherforcastmvvm.ui.weather.current.CurrentWeatherViewModelFactory
import com.mobile.akev.weatherforcastmvvm.ui.weather.future.details.FutureWeatherDetailsViewModelFactory
import com.mobile.akev.weatherforcastmvvm.ui.weather.future.list.FutureWeatherViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.*
import org.threeten.bp.LocalDate

class ForcastApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ForcastApplication))
        bind<ForcastDatabase>() with singleton { ForcastDatabase(instance()) }
        bind() from singleton { instance<ForcastDatabase>().currentWeatherDao() }
        bind() from singleton { instance<ForcastDatabase>().futureWeatherDao() }
        bind() from singleton { instance<ForcastDatabase>().weatherLocationDao() }
        bind<ConnectivityInterceptor>() with singleton { ConnectivityInterceptorImpl(instance()) }
        bind<WeatherApiService>() with singleton { WeatherApiService(instance()) }
        bind<NetworkWeatherDataSource>() with singleton { NetworkWeatherDataSourceImpl(instance()) }
        bind() from provider { LocationServices.getFusedLocationProviderClient(instance<Context>()) }
        bind<LocationProvider>() with singleton { LocationProviderImpl(instance(), instance()) }
        bind<ForcastRepository>() with singleton {
            ForcastRepositoryImpl(
                instance(),
                instance(),
                instance(),
                instance(),
                instance()
            )
        }
        bind<UnitProvider>() with singleton { UnitProviderImpl(instance()) }
        bind() from provider { CurrentWeatherViewModelFactory(instance(), instance()) }
        bind() from provider { FutureWeatherViewModelFactory(instance(), instance()) }
        bind() from factory { detailDate: LocalDate ->FutureWeatherDetailsViewModelFactory(detailDate,instance(), instance()) }
    }

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false)
    }
}