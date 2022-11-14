package com.sergio931.weatherapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import com.sergio931.weatherapp.data.repository.CitiesRepositoryImpl
import com.sergio931.weatherapp.data.repository.ForecastRepositoryImpl
import com.sergio931.weatherapp.domain.interactor.WeatherColorsInteractor
import com.sergio931.weatherapp.domain.interactor.WeatherStringsInteractor
import com.sergio931.weatherapp.domain.repository.CitiesRepository
import com.sergio931.weatherapp.domain.repository.ForecastRepository
import com.sergio931.weatherapp.presentation.interactor.WeatherColorsInteractorImpl
import com.sergio931.weatherapp.presentation.interactor.WeatherStringsInteractorImpl


@Module
@InstallIn(ViewModelComponent::class)
interface BindsModule {

    @Binds
    fun bindForecastRepository(forecastRepositoryImpl: ForecastRepositoryImpl): ForecastRepository

    @Binds
    fun bindCityRepository(cityRepositoryImpl: CitiesRepositoryImpl): CitiesRepository

    @Binds
    fun bindWeatherStringsInteractor(weatherStringsInteractorImpl: WeatherStringsInteractorImpl): WeatherStringsInteractor

    @Binds
    fun bindColorsInteractor(weatherColorsInteractorImpl: WeatherColorsInteractorImpl): WeatherColorsInteractor

}