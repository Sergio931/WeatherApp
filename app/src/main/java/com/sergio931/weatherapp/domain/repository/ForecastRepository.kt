package com.sergio931.weatherapp.domain.repository

import com.sergio931.weatherapp.domain.model.ForecastDay
import com.sergio931.weatherapp.domain.model.WeatherForecast

interface ForecastRepository {

    suspend fun getWeeklyForecast(
        lat: Double,
        lon: Double,
        units: String,
        lang: String,
    ): WeatherForecast

    suspend fun getDayForecast(
        lat: Double,
        lon: Double,
        units: String,
        lang: String,
    ) : ForecastDay
}