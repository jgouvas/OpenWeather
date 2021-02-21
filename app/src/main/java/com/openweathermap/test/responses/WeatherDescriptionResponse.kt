package com.openweathermap.test.responses

import com.google.gson.annotations.SerializedName

data class WeatherDescriptionResponse(
    @SerializedName("description") val description: String
)
