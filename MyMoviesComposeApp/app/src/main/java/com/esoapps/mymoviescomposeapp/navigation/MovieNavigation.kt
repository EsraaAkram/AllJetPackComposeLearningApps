package com.esoapps.mymoviescomposeapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.esoapps.mymoviescomposeapp.screens.MovieScreens
import com.esoapps.mymoviescomposeapp.screens.details.DetailsScreen
import com.esoapps.mymoviescomposeapp.screens.home.HomeScreen

@Composable
fun MovieNavigation() {

    val navController= rememberNavController()

    NavHost(
        navController =navController,
        startDestination = MovieScreens.HomeScreen.name,

       ){
        composable(
            MovieScreens.HomeScreen.name
        ){
            //PASS WHERE THIS SHOULD LEAD US TO
            HomeScreen(navController)
        }

        //www.google.com/whatever
        composable(
            MovieScreens.DetailsScreen.name+"/{movie}",
        arguments = listOf(navArgument(name = "movie"){
            type= NavType.StringType
        })){ backStackEntry->

            DetailsScreen(navController,
                backStackEntry.arguments?.getString("movie")
            )
        }

    }



}