package com.sergio931.weatherapp.presentation.navigation

import androidx.navigation.NavHostController
import com.sergio931.weatherapp.utils.Constants.SEARCH_SCREEN

class Screens(navHostController: NavHostController) {

    val search: () -> Unit = {
        navHostController.navigate(route = SEARCH_SCREEN)
    }

}