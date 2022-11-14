package com.sergio931.weatherapp.data.repository

import dagger.hilt.android.scopes.ViewModelScoped
import com.sergio931.weatherapp.data.api.WeatherApi
import com.sergio931.weatherapp.data.api.mapper.DtoToDomain
import com.sergio931.weatherapp.domain.repository.ForecastRepository
import javax.inject.Inject


@ViewModelScoped
class ForecastRepositoryImpl @Inject constructor(
    private val forecastApi: WeatherApi,
    private val mapper: DtoToDomain
) : ForecastRepository {

    override suspend fun getWeeklyForecast(
        lat: Double,
        lon: Double,
        units: String,
        lang: String,
    ) = mapper.map(forecastApi.getWeekForecast(lat = lat, lon = lon, units = units, lang = lang))

    override suspend fun getDayForecast(
        lat: Double,
        lon: Double,
        units: String,
        lang: String
    ) = mapper.map(forecastApi.getDayForecast(lat = lat, lon = lon, units = units, lang = lang))

}