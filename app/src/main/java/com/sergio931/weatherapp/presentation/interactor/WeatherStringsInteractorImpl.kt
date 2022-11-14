package com.sergio931.weatherapp.presentation.interactor

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import com.sergio931.weatherapp.R
import com.sergio931.weatherapp.domain.interactor.WeatherStringsInteractor
import javax.inject.Inject

class WeatherStringsInteractorImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : WeatherStringsInteractor {

    override val unknown: String
        get() = context.getString(R.string.unknown)

    override val today: String
        get() = context.getString(R.string.today)
}