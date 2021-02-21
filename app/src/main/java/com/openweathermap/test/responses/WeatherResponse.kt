package com.openweathermap.test.responses

import com.google.gson.annotations.SerializedName
import com.openweathermap.test.responses.ForecastListResponse

data class WeatherResponse(
    @SerializedName("cod") val cod: Int,
    @SerializedName("list") val list: List<ForecastListResponse>
)


