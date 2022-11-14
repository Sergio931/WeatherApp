package com.sergio931.weatherapp.presentation.interactor

import androidx.compose.ui.graphics.Color
import com.sergio931.weatherapp.domain.interactor.WeatherColorsInteractor
import com.sergio931.weatherapp.presentation.theme.*
import javax.inject.Inject

class WeatherColorsInteractorImpl @Inject constructor() : WeatherColorsInteractor {

    override val cloudsColor: Color
        get() = clouds
    override val drizzleColor: Color
        get() = drizzle
    override val foggyColor: Color
        get() = foggy
    override val rainColor: Color
        get() = rain
    override val sunColor: Color
        get() = sun
    override val thunderstormColor: Color
        get() = thunderstorm
    override val snowColor: Color
        get() = snow

}