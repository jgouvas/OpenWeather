package com.openweathermap.test

data class CityWeather(
    val temperature: String,
    val description: String,
    val date: String,
    val dt: Long,
    val dayNumber: Int
)
