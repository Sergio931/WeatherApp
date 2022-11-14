package com.sergio931.weatherapp.domain.repository

import com.sergio931.weatherapp.domain.model.CityItem

interface CitiesRepository {
    suspend fun getCities(
        namePrefix: String,
        languageCode: String
    ): List<CityItem>
}