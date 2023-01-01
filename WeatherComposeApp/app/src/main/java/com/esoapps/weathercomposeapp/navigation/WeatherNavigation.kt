package com.esoapps.weathercomposeapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.esoapps.weathercomposeapp.ui.screens.splash.SplashScreen
import com.esoapps.weathercomposeapp.ui.screens.about.AboutScreen
import com.esoapps.weathercomposeapp.ui.screens.fav.FavScreen
import com.esoapps.weathercomposeapp.ui.screens.fav.FavouriteViewModel
import com.esoapps.weathercomposeapp.ui.screens.main.MainScreen
import com.esoapps.weathercomposeapp.ui.screens.main.MainViewModel
import com.esoapps.weathercomposeapp.ui.screens.search.SearchScreen
import com.esoapps.weathercomposeapp.ui.screens.settings.SettingScreen
import com.esoapps.weathercomposeapp.ui.screens.settings.SettingsViewModel

@Composable
fun WeatherNavigation() {

    val navController = rememberNavController()

    val mainViewModel = hiltViewModel<MainViewModel>()
    val favouriteViewModel = hiltViewModel<FavouriteViewModel>()
    val settingsViewModel = hiltViewModel<SettingsViewModel>()

    NavHost(
        navController = navController,

        startDestination = WeatherScreens.SPLASH_SCREEN.name,
    ) {
        composable(route = WeatherScreens.SPLASH_SCREEN.name) {

            SplashScreen(navController = navController)
        }

        composable(route = "${WeatherScreens.MAIN_SCREEN.name}/{city}",
            arguments = listOf(
                navArgument("city") {
                    type = NavType.StringType
                }
            )) { navBack ->

            navBack.arguments?.getString("city").let { cityIt ->

                MainScreen(
                    navController = navController,
                    city = cityIt!!,

                )
            }

        }


        composable(route = WeatherScreens.SEARCH_SCREEN.name) {

            SearchScreen(
                navController = navController,
            )
        }

        composable(route = WeatherScreens.ABOUT_SCREEN.name) {

            AboutScreen(navController = navController)
        }



        composable(route = WeatherScreens.SETTINGS_SCREEN.name) {

            SettingScreen(
                navController = navController,
                settingsViewModel=settingsViewModel
            )
        }


        composable(route = WeatherScreens.FAVOURITE_SCREEN.name) {

            FavScreen(
                navController = navController,
                favouriteViewModel=favouriteViewModel
            )
        }

    }
}