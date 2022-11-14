package com.sergio931.weatherapp.data.api.dto.day_response


import com.google.gson.annotations.SerializedName

data class Clouds(
    @SerializedName("all")
    val all: Int
)