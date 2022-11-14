package com.sergio931.weatherapp.data.api.dto.city_response

import com.google.gson.annotations.SerializedName

data class Metadata(
    @SerializedName("currentOffset")
    val currentOffset: Int,
    @SerializedName("totalCount")
    val totalCount: Int
)