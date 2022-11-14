package com.sergio931.weatherapp.data.repository

import dagger.hilt.android.scopes.ViewModelScoped
import com.sergio931.weatherapp.data.api.CityApi
import com.sergio931.weatherapp.data.api.mapper.toDomain
import com.sergio931.weatherapp.domain.repository.CitiesRepository
import javax.inject.Inject

@ViewModelScoped
class CitiesRepositoryImpl @Inject constructor(
    private val cityApi: CityApi,
) : CitiesRepository {

    override suspend fun getCities(
        namePrefix: String,
        languageCode: String
    ) = cityApi.getCityList(namePrefix = namePrefix, languageCode = languageCode).toDomain()

}
