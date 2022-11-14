package com.sergio931.weatherapp.presentation.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.sergio931.weatherapp.domain.model.ForecastDay
import com.sergio931.weatherapp.domain.model.WeatherForecast
import com.sergio931.weatherapp.presentation.theme.AppThemes
import com.sergio931.weatherapp.presentation.theme.ralewayFontFamily

@ExperimentalMaterialApi
@ExperimentalCoroutinesApi
@Composable
fun ColumnScope.WeekForecastList(
    weatherForecast: WeatherForecast,
    currentTheme: AppThemes
) {
    val listState = rememberLazyListState()

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .weight(weight = 0.6f)
            .padding(all = 10.dp),
        contentPadding = PaddingValues(all = 15.dp),
        state = listState,
        horizontalAlignment = Alignment.Start
    ) {
        itemsIndexed(items = weatherForecast.forecastDays) { _, day ->
            ForecastItem(forecastDay = day, appThemes = currentTheme)
            Spacer(modifier = Modifier.width(50.dp))
        }
    }
}

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
fun ForecastItem(
    modifier: Modifier = Modifier,
    forecastDay: ForecastDay,
    appThemes: AppThemes,
) {

    Card(
        modifier = modifier.height(170.dp).padding(bottom = 20.dp),
        backgroundColor = appThemes.primaryColor,
    ){
        ForecastAdditionalInfo(forecastDay = forecastDay, currentTheme = appThemes)
    }
}

@ExperimentalCoroutinesApi
@Composable
fun ForecastAdditionalInfo(
    forecastDay: ForecastDay,
    currentTheme: AppThemes
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
            .background(currentTheme.primaryColor)
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = forecastDay.dayName.replace("\n", " "),
                    fontSize = 12.sp,
                    color = currentTheme.textColor
                )
            }
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_sunrise),
                    contentDescription = stringResource(R.string.sunrise_icon),
                    tint = currentTheme.iconsTint
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = forecastDay.sunrise, color = currentTheme.textColor, fontSize = 12.sp)
            }
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_sunset),
                    contentDescription = stringResource(R.string.sunset_icon),
                    tint = currentTheme.iconsTint
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = forecastDay.sunset, color = currentTheme.textColor, fontSize = 12.sp)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_humidity),
                    contentDescription = stringResource(R.string.humidity_icon),
                    tint = currentTheme.iconsTint
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "${forecastDay.dayHumidity}%",
                    color = currentTheme.textColor,
                    fontSize = 12.sp,
                    fontFamily = ralewayFontFamily
                )
            }
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_wind_icon),
                    contentDescription = stringResource(R.string.day_wind_icon),
                    tint = currentTheme.iconsTint
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = forecastDay.dayWindSpeed + stringResource(id = R.string.m_s)
                       ,
                    color = currentTheme.textColor,
                    fontSize = 12.sp,
                    fontFamily = ralewayFontFamily
                )
            }
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_pressure),
                    contentDescription = stringResource(R.string.day_pressure_icon),
                    tint = currentTheme.iconsTint
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = String.format(
                        stringResource(id = R.string.pressure_units_mm_hg),
                        forecastDay.dayPressure
                    ),
                    color = currentTheme.textColor,
                    fontSize = 12.sp,
                    fontFamily = ralewayFontFamily
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Row(
                modifier = Modifier.weight(1f),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_termometer),
                    contentDescription = stringResource(R.string.termometer_icon),
                    tint = currentTheme.iconsTint
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = forecastDay.dayTemp + "°",
                    color = currentTheme.textColor,
                    fontSize = 12.sp
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_tilda),
                contentDescription = stringResource(R.string.tilda_icon),
                modifier = Modifier
                    .width(10.dp)
                    .height(5.dp)
                    .weight(1f),
                tint = currentTheme.iconsTint
            )
            Text(
                text = forecastDay.tempFeelsLike + stringResource(id = R.string.temperature_units_celsius),
                modifier = Modifier.weight(1f),
                color = currentTheme.textColor,
                fontSize = 12.sp
            )
        }
    }
}