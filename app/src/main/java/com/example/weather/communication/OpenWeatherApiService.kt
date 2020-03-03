package com.example.weather.communication

import com.example.weather.model.OpenWeatherResponseModel
import com.example.weather.model.TemperatureModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

private val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .baseUrl(BASE_URL)
        .build()

    interface OpenWeatherApiService {
        @GET("weather?appid=ecd30955dddd11f91b0906955dbfd3d9&units=metric")
        fun getCurrentWeatherAsync(@Query("q") city: String) : Deferred<TemperatureModel>

        @GET("forecast?appid=ecd30955dddd11f91b0906955dbfd3d9&units=metric")
        fun getFutureWeatherAsync(@Query("q") city: String) : Deferred<OpenWeatherResponseModel>

    }

    object OpenWeatherApi{
        val retrofitService : OpenWeatherApiService by lazy {
            retrofit.create(OpenWeatherApiService::class.java)
        }
}