package com.sergio931.weatherapp.domain.model


data class ForecastDay(
    val dayName: String = "",
    val dayStatus: String = "",
    val dayTemp: String = "",
    val dayStatusId: Int = 0,
    val sunrise: String = "",
    val sunset: String = "",
    val tempFeelsLike: String = "",
    val dayPressure: String = "",
    val dayHumidity: String = "",
    val dayWindSpeed: String = "",
)