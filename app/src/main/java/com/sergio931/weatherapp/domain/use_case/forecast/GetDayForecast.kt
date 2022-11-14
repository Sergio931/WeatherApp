package com.sergio931.weatherapp.domain.use_case.forecast

import kotlinx.coroutines.flow.flow
import com.sergio931.weatherapp.domain.model.AppState
import com.sergio931.weatherapp.domain.model.ErrorState
import com.sergio931.weatherapp.domain.repository.ForecastRepository
import timber.log.Timber
import java.util.*
import javax.inject.Inject

class GetDayForecast @Inject constructor(
    private val forecastRepository: ForecastRepository
) {
    operator fun invoke(lat: Double, lon: Double) = flow {
        try {
            val dayForecast = forecastRepository.getDayForecast(
                lat = lat,
                lon = lon,
                units = "metric",
                lang = Locale.getDefault().language
            )
            emit(AppState.Success(data = dayForecast))
        } catch (e: Exception) {
            Timber.e(e)
            emit(AppState.Error(error = ErrorState.NO_FORECAST_LOADED))
        }
    }
}