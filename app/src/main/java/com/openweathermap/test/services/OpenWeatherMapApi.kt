package com.openweathermap.test.services

import com.openweathermap.test.responses.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface OpenWeatherMapApi {

    @GET("forecast")
    fun getCityWeather(
        @Header("x-rapidapi-key") apiKey: String,
        @Header("x-rapidapi-host") host: String,
        @Query("q") q: String,
        @Query("units") units: String,
    ): Single<WeatherResponse>
}