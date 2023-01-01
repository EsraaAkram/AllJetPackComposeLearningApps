package com.esoapps.weathercomposeapp.ui.screens.fav

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.esoapps.weathercomposeapp.model.Favourite
import com.esoapps.weathercomposeapp.navigation.WeatherScreens
import com.esoapps.weathercomposeapp.ui.widgets.WeatherAppToolBar

@Composable
fun FavScreen(
    navController: NavHostController,

    favouriteViewModel: FavouriteViewModel = hiltViewModel(),
    ) {

    Scaffold(
        topBar = {
            WeatherAppToolBar(
                title = "Fav",
                icon = Icons.Default.ArrowBack,
                isFromMain = false,

                navController = navController,
                favouriteViewModel=favouriteViewModel,
            ) {
                navController.popBackStack()

            }

        }
    ) {

        Surface(
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth()
        ) {

            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val favList = favouriteViewModel.favList
                    .collectAsState().value

                LazyColumn() {
                    items(favList) {
                        CityRow(
                            favourite = it,
                            navController = navController,
                            favouriteViewModel = favouriteViewModel
                        )

                    }
                }
            }

        }

    }

}

@Composable
fun CityRow(
    favourite: Favourite,
    navController: NavHostController,
    favouriteViewModel: FavouriteViewModel
) {

    Surface(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clickable {
                navController.navigate(
                    WeatherScreens.MAIN_SCREEN.name
                            + "/${favourite.city}"
                )
            }
        ,
        shape = CircleShape.copy(topEnd = CornerSize(5.dp)),
        color = Color(0xFFAF923B)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Text(
                modifier = Modifier
                    .padding(start = 5.dp),
                text = favourite.city,
                style = MaterialTheme.typography.caption
            )

            Surface(
                modifier = Modifier
                    .padding(3.dp),
                shape = CircleShape,
                color = Color(0xFFEEE8AF)
            ) {
                Text(
                    modifier = Modifier
                        .padding(start = 5.dp),
                    text = favourite.country,
                    style = MaterialTheme.typography.caption
                )
            }

            Icon(
                modifier = Modifier.clickable {
                    favouriteViewModel.deleteFavCity(favourite)

                },
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete Icon",
                tint = Color.Red.copy(alpha = .3F),
            )

        }


    }

}
