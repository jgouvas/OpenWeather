package com.openweathermap.test.responses

import com.google.gson.annotations.SerializedName

data class ForecastDetailsResponse(
    @SerializedName("temp") val temp: Float
)
