package com.openweathermap.test.services

import android.annotation.SuppressLint
import com.openweathermap.test.CityWeather
import com.openweathermap.test.responses.ForecastListResponse
import com.openweathermap.test.utils.DateCalculationUtils
import io.reactivex.Single
import java.text.SimpleDateFormat
import java.util.*

class WeatherInteract(private val openWeatherService: OpenWeatherMapService) {

    fun getPortoWeather(units: String): Single<List<CityWeather>> {
        return openWeatherService.getPortoWeather(units)
            .map {
                mapResponseToCityWeatherList(it.list)
            }
    }

    @SuppressLint("SimpleDateFormat")
    private fun mapResponseToCityWeatherList(forecastList: List<ForecastListResponse>): List<CityWeather> {
        val currentDateUnix = DateCalculationUtils.getStartOfDayInMillis() / 1000
        val cityWeatherList = ArrayList<CityWeather>()

        val forecastIterator = forecastList.listIterator()

        for (response in forecastIterator) {
            val dateFormatter = SimpleDateFormat("dd/MM/yyyy")
            val dayDiff = DateCalculationUtils.diffBetweenDates(currentDateUnix, response.dt)
            val cityWeather =
                CityWeather(
                    String.format("%.1f", response.main.temp),
                    response.weather[0].description,
                    dateFormatter.format(Date(response.dt * 1000L)),
                    response.dt,
                    dayDiff
                )
            cityWeatherList.add(cityWeather)
        }
        return cityWeatherList
    }
}