package com.sergio931.weatherapp.domain.use_case.forecast

import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import com.sergio931.weatherapp.domain.model.AppState
import com.sergio931.weatherapp.domain.model.ErrorState
import com.sergio931.weatherapp.domain.repository.ForecastRepository
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class GetWeeklyForecast @Inject constructor(
    private val forecastRepository: ForecastRepository
) {
    operator fun invoke(
        lat: Double,
        lon: Double,
    ) = flow {
        emit(AppState.Loading())
        try {
            val response =
                forecastRepository.getWeeklyForecast(lat, lon, "metric", lang = Locale.getDefault().language)
            emit(AppState.Success(data = response))
        } catch (exception: HttpException) {
            if (exception.code() != 400)
                emit(AppState.Error(error = ErrorState.NO_INTERNET_CONNECTION))
            Timber.e(exception)
        } catch (exception: Exception) {
            emit(AppState.Error(error = ErrorState.NO_INTERNET_CONNECTION))
            Timber.e(exception)
        }
    }


}