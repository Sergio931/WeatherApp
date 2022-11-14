package com.sergio931.weatherapp.presentation.navigation.destinations

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.sergio931.weatherapp.presentation.screens.home.HomeScreen
import com.sergio931.weatherapp.utils.Constants.HOME_SCREEN

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalPermissionsApi
@ExperimentalAnimationApi
fun NavGraphBuilder.homeComposable(
    navigateToSearchScreen: () -> Unit
) {
    composable(route = HOME_SCREEN) {
        HomeScreen(
            navigateToSearchScreen = navigateToSearchScreen
        )
    }
}