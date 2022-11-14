package com.sergio931.weatherapp.presentation.screens.search

import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.insets.systemBarsPadding
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.sergio931.weatherapp.presentation.theme.sun
import com.sergio931.weatherapp.presentation.viewmodel.SearchViewModel


@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
fun SearchScreen(
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val systemUiController = rememberSystemUiController()
    val searchedTextState by searchViewModel.searchTextState.collectAsState()

    SideEffect() {
        systemUiController.setStatusBarColor(darkIcons = true, color = sun)
        systemUiController.setNavigationBarColor(color = sun, darkIcons = true)
    }

    Box(modifier = Modifier.systemBarsPadding()) {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                SearchAppBar(
                    searchedTextState = searchedTextState,
                    onTextChange = { searchViewModel.updateTextState(it) },
                    onSearchClicked = { searchViewModel.getCitiesList() })
            },
            content = {
                SearchScreenContent(
                    onSearchedCityClicked = { coordinates ->
                        searchViewModel.getForecast(coordinates = coordinates)
                    })
            },
            floatingActionButton = {
                SearchCityFab(onSearchClicked = searchViewModel::getCitiesList)
            }
        )
    }
}

@Composable
fun SearchCityFab(onSearchClicked: () -> Unit) {
    FloatingActionButton(
        onClick = { onSearchClicked() },
        backgroundColor = sun
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(R.string.icon_search),
            tint = Color.White
        )
    }
}