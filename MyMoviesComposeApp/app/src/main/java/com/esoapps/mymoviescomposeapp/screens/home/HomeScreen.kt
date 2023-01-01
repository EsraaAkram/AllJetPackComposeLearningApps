package com.esoapps.mymoviescomposeapp.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.esoapps.mymoviescomposeapp.model.Movie
import com.esoapps.mymoviescomposeapp.model.getMovies
import com.esoapps.mymoviescomposeapp.screens.MovieScreens
import com.esoapps.mymoviescomposeapp.screens.details.DetailsScreen
import com.esoapps.mymoviescomposeapp.widgets.MyMainContent

@Composable
fun HomeScreen(navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.Red,
                elevation = 5.dp,

                ) {

                Text(text = "Movies List")
            }
        },
    ) {

        //content()
        MyMainContent(navController)

    }



}

