package com.sergio931.weatherapp.utils

import androidx.compose.ui.unit.dp

object Constants {

    const val HOME_SCREEN_BACKGROUND_ANIMATION_DURATION = 1000
    const val CITY_CARD_ANIMATION_DURATION = 300L
    val TOP_APPBAR_HEIGHT = 56.dp

    const val HOME_SCREEN = "home"
    const val SEARCH_SCREEN = "search"

    const val WEATHER_API_URL = "https://api.openweathermap.org"
    const val CITY_API_URL = "http://geodb-free-service.wirefreethought.com/"

    val thunderstorm_ids_range = 200..232
    val drizzle_ids_range = 300..321
    val rain_ids_range = 500..531
    val snow_ids_range = 600..622
    val atmosphere_ids_range = 701..781
    val clouds_ids_range = 801..804

}