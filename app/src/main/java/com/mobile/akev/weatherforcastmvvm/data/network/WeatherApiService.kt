package com.mobile.akev.weatherforcastmvvm.data.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.mobile.akev.weatherforcastmvvm.BuildConfig
import com.mobile.akev.weatherforcastmvvm.data.network.response.CurrentWeatherResponse
import com.mobile.akev.weatherforcastmvvm.data.network.response.FutureWeatherResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApiService {
    // http://api.weatherapi.com/v1/current.json?key=152ec0b965044815b8421108201403&q=20171
    @GET(value = "current.json")
    fun getCurrentWeather(
        @Query(value = "q") location: String,
        @Query(value = "lang") languageCode: String = "en"
    ): Deferred<CurrentWeatherResponse>

    // http://api.weatherapi.com/v1/forecast.json?key=152ec0b965044815b8421108201403&q=20171&days=1
    @GET(value = "forecast.json")
    fun getFutureWeather(
        @Query(value = "q") location: String,
        @Query(value = "days") days: Int,
        @Query(value = "lang") languageCode: String = "en"
    ): Deferred<FutureWeatherResponse>


    companion object {
        operator fun invoke(connectivityInterceptor: ConnectivityInterceptor): WeatherApiService {
            val requestInterceptor = Interceptor { chain ->
                val modifyUrl = chain.request()
                    .url()
                    .newBuilder()
                    .addQueryParameter(
                        "key",
                        BuildConfig.WEATHER_API_KEY
                    )
                    .build()
                val request = chain.request().newBuilder().url(modifyUrl).build()
                return@Interceptor chain.proceed(request)
            }
            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(requestInterceptor)
                .addInterceptor(connectivityInterceptor)
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.weatherapi.com/v1/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherApiService::class.java)
        }
    }
}
