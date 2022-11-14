package com.sergio931.weatherapp.presentation.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import com.sergio931.weatherapp.presentation.navigation.destinations.homeComposable
import com.sergio931.weatherapp.presentation.navigation.destinations.searchComposable
import com.sergio931.weatherapp.utils.Constants.HOME_SCREEN

@ExperimentalComposeUiApi
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalPermissionsApi
@ExperimentalAnimationApi
@Composable
fun SetupNavigation(navHostController: NavHostController) {
    val screens = remember(navHostController) { Screens(navHostController = navHostController) }

    AnimatedNavHost(navController = navHostController, startDestination = HOME_SCREEN) {
        homeComposable(
            navigateToSearchScreen = screens.search
        )
        searchComposable()
    }
}