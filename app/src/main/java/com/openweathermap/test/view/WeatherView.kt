package com.openweathermap.test.view

import com.github.mikephil.charting.data.Entry
import com.openweathermap.test.CityWeather


interface WeatherView {

    fun showLoading()
    fun hideLoading()
    fun setupTempratureGraph(cityWeatherList: List<Entry>)
    fun setupDayForecast(dayNumber: Int, cityWeatherList: List<CityWeather>)
}