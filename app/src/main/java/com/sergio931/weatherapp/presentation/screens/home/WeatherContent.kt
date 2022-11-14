package com.sergio931.weatherapp.presentation.screens.home

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.sergio931.weatherapp.R
import com.sergio931.weatherapp.domain.model.AppState
import com.sergio931.weatherapp.domain.model.ErrorState
import com.sergio931.weatherapp.domain.model.WeatherForecast
import com.sergio931.weatherapp.presentation.theme.AppThemes
import com.sergio931.weatherapp.presentation.theme.ralewayFontFamily
import com.sergio931.weatherapp.presentation.viewmodel.MainViewModel
import com.sergio931.weatherapp.utils.Constants

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun WeatherContent(
    currentTheme: AppThemes,
    weatherForecast: AppState<WeatherForecast>,
    navigateToSearchScreen: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()

    Scaffold(scaffoldState = scaffoldState) {
        Box {
            Crossfade(
                targetState = currentTheme.backgroundColor,
                animationSpec = tween(Constants.HOME_SCREEN_BACKGROUND_ANIMATION_DURATION)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(currentTheme.backgroundColor)
                )

            }
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .navigationBarsPadding()
            ) {
                Column(
                    modifier = Modifier
                        .weight(weight = 1f)
                        .fillMaxSize()
                ) {
                    when (weatherForecast) {
                        is AppState.Error -> weatherForecast.message?.let {
                            ErrorContent(errorState = it)
                        }
                        is AppState.Loading -> LoadingContent()
                        is AppState.Success -> weatherForecast.data?.let {
                            LocationContent(
                                weatherForecast = it,
                            )
                            CurrentWeatherContent(
                                weatherForecast = it,
                            )
                            WeekForecastList(
                                currentTheme = currentTheme,
                                weatherForecast = it
                            )
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .statusBarsPadding()
            ) {
                IconButton(onClick = { navigateToSearchScreen() }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.icon_search),
                        modifier = Modifier
                            .size(45.dp)
                            .padding(end = 10.dp),
                        tint = currentTheme.iconsTint
                    )
                }
            }
        }
    }
}

@Composable
fun LoadingContent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(modifier = Modifier.size(20.dp))
    }
}

@Composable
fun ErrorContent(errorState: ErrorState) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(fraction = .8f),
            text = when (errorState) {
                ErrorState.NO_INTERNET_CONNECTION -> stringResource(id = R.string.no_internet_connection)
                ErrorState.NO_FORECAST_LOADED -> stringResource(id = R.string.connection_error)
                ErrorState.NO_LOCATION_AVAILABLE -> stringResource(id = R.string.location_error)
                else -> stringResource(id = R.string.unknown_error)
            },
            color = Color.Black,
            fontFamily = ralewayFontFamily,
            fontSize = MaterialTheme.typography.h5.fontSize,
            textAlign = TextAlign.Center,
        )
    }
}

@ExperimentalCoroutinesApi
@Composable
fun ColumnScope.LocationContent(
    weatherForecast: WeatherForecast,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .weight(weight = 0.2f),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_location),
            contentDescription = stringResource(R.string.icon_location),
            modifier = Modifier
                .size(45.dp)
        )
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = weatherForecast.location,
                fontFamily = ralewayFontFamily,
                fontSize = 27.sp,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@ExperimentalCoroutinesApi
@Composable
fun ColumnScope.CurrentWeatherContent(
    weatherForecast: WeatherForecast,
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val isForecastLoading by mainViewModel.forecastLoading.collectAsState()

    var currentRotationAngle by remember { mutableStateOf(0f) }
    val rotation = remember { Animatable(currentRotationAngle) }

    LaunchedEffect(key1 = isForecastLoading) {
        if (isForecastLoading) {
            rotation.animateTo(
                targetValue = currentRotationAngle + 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(500, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            ) {
                currentRotationAngle = value
            }
        } else {
            if (currentRotationAngle > 0f) {
                rotation.animateTo(
                    targetValue = currentRotationAngle + 180f,
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = LinearOutSlowInEasing
                    )
                ) {
                    currentRotationAngle = value
                }
            }
        }
    }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .weight(weight = 0.3f)
    ) {
        Row {
            Text(
                text = weatherForecast.currentWeatherStatus,
                fontFamily = ralewayFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 35.sp
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = weatherForecast.currentWeather + "°",
                fontFamily = ralewayFontFamily,
                fontWeight = FontWeight.Light,
                fontSize = 60.sp
            )
            Column {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_wind_icon),
                        contentDescription = stringResource(R.string.icon_wind),
                        modifier = Modifier
                            .size(40.dp)
                            .padding(
                                start = 20.dp,
                                top = 0.dp,
                                end = 0.dp,
                                bottom = 0.dp
                            ),
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 5.dp, top = 0.dp, end = 0.dp, bottom = 0.dp),
                        text = weatherForecast.currentWindSpeed + stringResource(id = R.string.m_s),
                        fontFamily = ralewayFontFamily,
                        fontWeight = FontWeight.Light,
                        fontSize = 18.sp
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_humidity),
                        contentDescription = stringResource(R.string.icon_humidity),
                        modifier = Modifier
                            .size(30.dp)
                            .padding(
                                start = 20.dp,
                                top = 0.dp,
                                end = 0.dp,
                                bottom = 0.dp
                            ),
                    )
                    Text(
                        modifier = Modifier
                            .padding(start = 5.dp, top = 0.dp, end = 0.dp, bottom = 0.dp),
                        text = weatherForecast.currentHumidity,
                        fontFamily = ralewayFontFamily,
                        fontWeight = FontWeight.Light,
                        fontSize = 18.sp
                    )
                }
            }
        }
    }
}
