package com.sergio931.weatherapp.data.api

import retrofit2.http.GET
import retrofit2.http.Query
//import com.sergio931.weatherapp.BuildConfig
import com.sergio931.weatherapp.data.api.dto.day_response.DayForecastResponse
import com.sergio931.weatherapp.data.api.dto.week_response.ForecastResponse
import javax.inject.Singleton

@Singleton
interface WeatherApi {

    @GET(value = "/data/2.5/onecall")
    suspend fun getWeekForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String,
        @Query("lang") lang: String,
        //@Query("appid") appid: String = BuildConfig.weather_key
    ) : ForecastResponse

    @GET(value = "/data/2.5/weather")
    suspend fun getDayForecast(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String,
        @Query("lang") lang: String,
        //@Query("appid") appid: String = BuildConfig.weather_key
    ): DayForecastResponse

}