package com.openweathermap.test.responses

import com.google.gson.annotations.SerializedName

data class ForecastListResponse(
    @SerializedName("dt") val dt: Long,
    @SerializedName("main") val main: ForecastDetailsResponse,
    @SerializedName("weather") val weather: List<WeatherDescriptionResponse>
)

