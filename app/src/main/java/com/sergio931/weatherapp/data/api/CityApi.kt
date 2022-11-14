package com.sergio931.weatherapp.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import com.sergio931.weatherapp.data.api.dto.city_response.CitiesResponse
import javax.inject.Singleton

@Singleton
interface CityApi {

    @GET(value = "/v1/geo/cities")
    suspend fun getCityList(
        @Query("namePrefix") namePrefix: String,
        @Query("languageCode") languageCode: String
    ): CitiesResponse
}