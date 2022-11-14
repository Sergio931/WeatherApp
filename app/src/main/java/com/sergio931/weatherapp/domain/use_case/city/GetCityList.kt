package com.sergio931.weatherapp.domain.use_case.city

import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import com.sergio931.weatherapp.domain.model.AppState
import com.sergio931.weatherapp.domain.model.ErrorState
import com.sergio931.weatherapp.domain.repository.CitiesRepository
import timber.log.Timber
import java.util.*
import javax.inject.Inject


class GetCityList @Inject constructor(
    private val citiesRepository: CitiesRepository
) {
    operator fun invoke(cityName: String) = flow {
        emit(AppState.Loading())
        try {
            val response =
                citiesRepository.getCities(cityName, Locale.getDefault().language)
            if (response.isNullOrEmpty()) emit(AppState.Error(error = ErrorState.EMPTY_RESULT))
            else emit(AppState.Success(data = response))
        } catch (exception: HttpException) {
            emit(AppState.Error(error = ErrorState.NO_INTERNET_CONNECTION))
            Timber.e(exception)
        } catch (exception: Exception) {
            emit(AppState.Error(error = ErrorState.NO_INTERNET_CONNECTION))
            Timber.e(exception)
        }
    }


}