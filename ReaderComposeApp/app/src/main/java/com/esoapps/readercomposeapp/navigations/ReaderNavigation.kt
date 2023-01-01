package com.esoapps.readercomposeapp.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.esoapps.readercomposeapp.screens.ReaderSplashScreen
import com.esoapps.readercomposeapp.screens.details.BookDetailsScreen
import com.esoapps.readercomposeapp.screens.home.HomeScreen
import com.esoapps.readercomposeapp.screens.login.LoginScreen
import com.esoapps.readercomposeapp.screens.search.BookReaderSearchScreen
import com.esoapps.readercomposeapp.screens.stats.BookStatsScreen
import com.esoapps.readercomposeapp.screens.update.ReaderBookUpdateScreen

@Composable
fun ReaderNavigation() {

    var navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = ReaderScreens.SplashScreen.name,
    ) {


        composable(ReaderScreens.SplashScreen.name) { navBackStackEntry ->

            ReaderSplashScreen(navController=navController)

        }


        composable(ReaderScreens.LoginScreen.name) { navBackStackEntry ->

            LoginScreen(navController=navController,)

        }

        composable(ReaderScreens.CreateAccountScreen.name) { navBackStackEntry ->

            LoginScreen(navController=navController)

        }

        composable(ReaderScreens.ReaderHomeScreen.name) { navBackStackEntry ->

            HomeScreen(navController=navController)

        }

        composable(ReaderScreens.SearchScreen.name) { navBackStackEntry ->

            BookReaderSearchScreen(navController=navController)

        }

        composable(ReaderScreens.DetailsScreen.name) { navBackStackEntry ->

            BookDetailsScreen(navController=navController)

        }


        composable(ReaderScreens.UpdateScreen.name) { navBackStackEntry ->

            ReaderBookUpdateScreen(navController=navController)

        }
        composable(ReaderScreens.ReaderStatsScreen.name) { navBackStackEntry ->

            BookStatsScreen(navController=navController)

        }

    }


}