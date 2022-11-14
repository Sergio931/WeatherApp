package com.sergio931.weatherapp.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import com.sergio931.weatherapp.domain.model.AppState
import com.sergio931.weatherapp.domain.model.CityItem
import com.sergio931.weatherapp.domain.model.ForecastDay
import com.sergio931.weatherapp.domain.use_case.city.GetCityList
import com.sergio931.weatherapp.domain.use_case.forecast.GetDayForecast
import com.sergio931.weatherapp.utils.Constants.CITY_CARD_ANIMATION_DURATION
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getCityList: GetCityList,
    private val getDayForecast: GetDayForecast,
) : ViewModel() {

    private val _searchTextState = MutableStateFlow("")
    val searchTextState = _searchTextState.asStateFlow()

    private val _searchedCitiesList =
        MutableStateFlow<AppState<List<CityItem>>>(AppState.Success(listOf()))
    val searchedCitiesList = _searchedCitiesList.asStateFlow()

    private val _dayForecast = MutableStateFlow<AppState<ForecastDay>>(AppState.Loading())
    val dayForecast = _dayForecast.asStateFlow()

    fun getCitiesList() = getCityList(searchTextState.value).onEach { result ->
        _searchedCitiesList.value = result
    }.launchIn(viewModelScope)

    fun getForecast(coordinates: Pair<Double, Double>) {
        _dayForecast.value = AppState.Loading()
        getDayForecast(
            coordinates.first,
            coordinates.second,
        ).onEach { result ->
            delay(CITY_CARD_ANIMATION_DURATION)
            _dayForecast.value = result
        }.launchIn(viewModelScope)
    }


    fun updateTextState(text: String) {
        _searchTextState.value = text
    }


}