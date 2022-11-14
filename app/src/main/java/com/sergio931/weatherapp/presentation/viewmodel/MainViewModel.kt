package com.sergio931.weatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import com.sergio931.weatherapp.domain.model.AppState
import com.sergio931.weatherapp.domain.model.ErrorState
import com.sergio931.weatherapp.domain.model.WeatherForecast
import com.sergio931.weatherapp.domain.use_case.forecast.GetWeeklyForecast
import com.sergio931.weatherapp.presentation.theme.AppThemes
import com.sergio931.weatherapp.utils.Constants.atmosphere_ids_range
import com.sergio931.weatherapp.utils.Constants.clouds_ids_range
import com.sergio931.weatherapp.utils.Constants.drizzle_ids_range
import com.sergio931.weatherapp.utils.Constants.rain_ids_range
import com.sergio931.weatherapp.utils.Constants.snow_ids_range
import com.sergio931.weatherapp.utils.Constants.thunderstorm_ids_range
import com.sergio931.weatherapp.utils.compare
import com.sergio931.weatherapp.utils.location.LocationListener
import com.sergio931.weatherapp.utils.network.ConnectionState
import com.sergio931.weatherapp.utils.network.NetworkStatusListener
import timber.log.Timber
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledFuture
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class MainViewModel @Inject constructor(
    networkStatusListener: NetworkStatusListener,
    private val getWeeklyForecast: GetWeeklyForecast,
    private val locationListener: LocationListener
) : ViewModel() {

    private val _currentLocation = MutableStateFlow(Pair(-200.0, -200.0))

    private val _forecastLoading = MutableStateFlow(true)
    val forecastLoading = _forecastLoading.asStateFlow()

    private val _currentTheme = MutableStateFlow<AppThemes>(AppThemes.SunnyTheme())
    val currentTheme = _currentTheme.asStateFlow()

    private val _weatherForecast = MutableStateFlow<AppState<WeatherForecast>>(AppState.Loading())
    val weatherForecast = _weatherForecast.asStateFlow()

    private val scheduledExecutorService = Executors.newScheduledThreadPool(1)
    private var future: ScheduledFuture<*>? = null

    init{

        networkStatusListener.networkStatus.onEach { status ->
            when (status) {
                ConnectionState.Available -> {
                    if (_weatherForecast !is AppState.Loading<*>) getWeatherForecast()
                }
                ConnectionState.Unavailable -> {
                    if (_weatherForecast.value.data != null) _weatherForecast.value =
                        AppState.Error(error = ErrorState.NO_INTERNET_CONNECTION)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun getWeatherForecast() {
        getWeeklyForecast(
            lat = _currentLocation.value.first,
            lon = _currentLocation.value.second,
        ).onEach { result ->
            when (result) {
                is AppState.Success -> {
                    _currentTheme.value = selectTheme(result.data?.currentWeatherStatusId)
                    _weatherForecast.value = result
                    _forecastLoading.value = false
                }
                is AppState.Loading -> {
                    _forecastLoading.value = true
                    if (future?.isCancelled == false) future?.cancel(false)
                }
                is AppState.Error -> {
                    _forecastLoading.value = false
                    _weatherForecast.value = result
                    Timber.e(result.message?.name)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun observeCurrentLocation() = locationListener.currentLocation.onEach { locationResult ->
        when (locationResult) {
            is AppState.Error -> {
                Timber.e(locationResult.message?.name)
            }
            is AppState.Loading -> Timber.d(message = "Location loading")
            is AppState.Success -> {
                locationResult.data?.let { coordinates ->
                    if (coordinates.compare(_currentLocation.value)) return@let
                    _currentLocation.value = coordinates
                    getWeatherForecast()
                }
            }
        }
    }.launchIn(viewModelScope)

    private fun selectTheme(currentWeatherStatusId: Int?): AppThemes =
        if (currentWeatherStatusId != null) {
            when (currentWeatherStatusId) {
                in rain_ids_range -> AppThemes.RainyTheme()
                in clouds_ids_range -> AppThemes.CloudyTheme()
                in atmosphere_ids_range -> AppThemes.AtmosphereTheme()
                in snow_ids_range -> AppThemes.SnowTheme()
                in drizzle_ids_range -> AppThemes.DrizzleTheme()
                in thunderstorm_ids_range -> AppThemes.ThunderstormTheme()
                else -> AppThemes.SunnyTheme()
            }
        } else AppThemes.SunnyTheme()



    override fun onCleared() {
        super.onCleared()
        future?.cancel(false)
    }
}
