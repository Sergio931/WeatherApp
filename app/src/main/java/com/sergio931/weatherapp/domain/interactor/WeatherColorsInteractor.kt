package com.sergio931.weatherapp.domain.interactor

import androidx.compose.ui.graphics.Color

interface WeatherColorsInteractor {
    val cloudsColor: Color
    val drizzleColor: Color
    val foggyColor: Color
    val rainColor: Color
    val sunColor: Color
    val thunderstormColor: Color
    val snowColor: Color
}