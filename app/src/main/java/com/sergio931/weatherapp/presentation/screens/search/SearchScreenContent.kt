package com.sergio931.weatherapp.presentation.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sergio931.weatherapp.domain.model.AppState
import com.sergio931.weatherapp.domain.model.ForecastDay
import com.sergio931.weatherapp.presentation.theme.ralewayFontFamily
import com.sergio931.weatherapp.presentation.theme.sun
import com.sergio931.weatherapp.R
import com.sergio931.weatherapp.domain.model.CityItem
import com.sergio931.weatherapp.presentation.components.ExpandableCard
import com.sergio931.weatherapp.presentation.viewmodel.SearchViewModel

@ExperimentalMaterialApi
@Composable
fun SearchScreenContent(
    onSearchedCityClicked: (Pair<Double, Double>) -> Unit,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val searchedCities by searchViewModel.searchedCitiesList.collectAsState()

    val cityDayForecast by searchViewModel.dayForecast.collectAsState()

    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        when (searchedCities) {
            is AppState.Error -> EmptyCityList()
            is AppState.Loading -> CitiesLoading()
            is AppState.Success -> searchedCities.data?.let { searchedCities ->
                Spacer(modifier = Modifier.height(10.dp))
                DisplayCities(
                    cities = searchedCities,
                    cityDayForecast = cityDayForecast,
                    onCityClicked = { onSearchedCityClicked(it) },
                )
            }
        }
    }
}

@Composable
fun CitiesLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.size(30.dp), color = sun)
    }
}

@ExperimentalMaterialApi
@Composable
fun DisplayCities(
    cities: List<CityItem>,
    cityDayForecast: AppState<ForecastDay>,
    onCityClicked: (Pair<Double, Double>) -> Unit,
) {
    var lastExpandedItemPosition by remember { mutableStateOf(-1) }

    Column(modifier = Modifier.padding(horizontal = 15.dp)) {
        cities.forEachIndexed { position, element ->
            CityItem(
                city = element,
                cityDayForecast = cityDayForecast,
                isExpanded = lastExpandedItemPosition != -1
                        && cities[lastExpandedItemPosition] == element,
                onCityClicked = {
                    lastExpandedItemPosition =
                        if (position != lastExpandedItemPosition) {
                            onCityClicked(it)
                            position
                        } else -1
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun CityItem(
    city: CityItem,
    cityDayForecast: AppState<ForecastDay>,
    isExpanded: Boolean,
    onCityClicked: (Pair<Double, Double>) -> Unit,
) {


    Box() {
            ExpandableCard(
                title = city.name,
                onCardClick = { onCityClicked(Pair(city.latitude, city.longitude)) },
                descriptionBlock = {
                    when (cityDayForecast) {
                        is AppState.Error -> DayForecastError()
                        is AppState.Loading -> DayForecastLoading()
                        is AppState.Success -> cityDayForecast.data?.let {
                            CityDayForecast(dayForecast = it)
                        }
                    }
                },
                isExpanded = isExpanded,
                textFontFamily = ralewayFontFamily,
            )
    }
}

@Composable
fun DayForecastLoading() {
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        CircularProgressIndicator(modifier = Modifier.size(20.dp), color = Color.Red)
    }
}

@Composable
fun DayForecastError() {
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        Text(
            text = stringResource(R.string.load_error_message),
            color = sun,
            fontFamily = ralewayFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 17.sp
        )
    }
}

@Composable
fun CityDayForecast(
    dayForecast: ForecastDay
) {

    var size by remember { mutableStateOf(IntSize.Zero) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(5.dp)
            .onGloballyPositioned { size = it.size }
    ) {
        Column(
            modifier = Modifier
                .weight(1.4f)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.padding(bottom = 10.dp),
                text = dayForecast.dayName,
                color = Color.Black,
                fontFamily = ralewayFontFamily,
                fontSize = 18.sp
            )
            Text(
                text = dayForecast.dayTemp + stringResource(id = R.string.temperature_units_celsius)
                    ,
                color = Color.Black,
                fontFamily = ralewayFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp
            )
            Text(
                text = dayForecast.dayStatus,
                color = Color.Black,
                fontFamily = ralewayFontFamily,
                fontWeight = FontWeight.Medium
            )
        }
        Column(
            modifier = Modifier
                .weight(4f)
                .height(with(LocalDensity.current) { size.height.toDp() }),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sunrise),
                            contentDescription = stringResource(id = R.string.sunrise_icon)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = dayForecast.sunrise,
                            color = Color.Black,
                            fontFamily = ralewayFontFamily,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_sunset),
                            contentDescription = stringResource(id = R.string.sunset_icon)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = dayForecast.sunset,
                            color = Color.Black,
                            fontFamily = ralewayFontFamily,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
            Row {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_humidity),
                            contentDescription = stringResource(id = R.string.humidity_icon)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = dayForecast.dayHumidity,
                            color = Color.Black,
                            fontFamily = ralewayFontFamily,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_wind_icon),
                            contentDescription = stringResource(id = R.string.day_wind_icon)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = dayForecast.dayWindSpeed + stringResource(id = R.string.m_s),
                            color = Color.Black,
                            fontFamily = ralewayFontFamily,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_pressure),
                    contentDescription = stringResource(id = R.string.day_pressure_icon)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = String.format(
                        stringResource(id = R.string.pressure_units_mm_hg),
                        dayForecast.dayPressure
                    ),
                    fontFamily = ralewayFontFamily
                )
            }
        }
    }
}
