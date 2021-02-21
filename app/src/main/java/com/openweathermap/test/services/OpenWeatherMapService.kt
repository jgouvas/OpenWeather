package com.openweathermap.test.services

import com.openweathermap.test.BuildConfig
import com.openweathermap.test.responses.WeatherResponse
import io.reactivex.Single

class OpenWeatherMapService(private val openWeatherApi: OpenWeatherMapApi) {

    fun getPortoWeather(units: String): Single<WeatherResponse> {
        return openWeatherApi.getCityWeather(
            BuildConfig.OPEN_WEATHER_API_KEY,
            BuildConfig.OPEN_WEATHER_HOST,
            PORTO_CITY,
            units
        )
    }

    companion object {
        const val PORTO_CITY = "porto,pt"
    }
}