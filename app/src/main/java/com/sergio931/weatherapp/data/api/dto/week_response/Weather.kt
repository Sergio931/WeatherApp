package com.sergio931.weatherapp.data.api.dto.week_response

import com.google.gson.annotations.SerializedName

data class Weather(
    @SerializedName("id") val id: Double,
    @SerializedName("main") val main: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)