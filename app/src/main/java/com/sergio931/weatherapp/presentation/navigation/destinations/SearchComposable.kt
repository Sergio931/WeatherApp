package com.sergio931.weatherapp.presentation.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.sergio931.weatherapp.presentation.screens.search.SearchScreen
import com.sergio931.weatherapp.utils.Constants.SEARCH_SCREEN

@ExperimentalComposeUiApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalPermissionsApi
@ExperimentalAnimationApi
fun NavGraphBuilder.searchComposable() {

    composable(route = SEARCH_SCREEN) {
        SearchScreen()
    }
}