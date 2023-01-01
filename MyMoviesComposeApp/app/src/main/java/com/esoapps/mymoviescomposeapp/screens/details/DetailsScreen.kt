package com.esoapps.mymoviescomposeapp.screens.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.esoapps.mymoviescomposeapp.model.Movie
import com.esoapps.mymoviescomposeapp.model.getMovies
import com.esoapps.mymoviescomposeapp.widgets.MovieRow

@Composable
fun DetailsScreen(navController: NavController,
                  //movieName: String?,
                  movieId: String?,
) {

   var movieList= getMovies().filter { movie ->
       movie.id==movieId

   }

    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.Blue) {

            Row (horizontalArrangement = Arrangement.Start){
                Icon(
                    modifier = Modifier.clickable {

                        navController.popBackStack()
                    },
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                )

                Spacer(modifier = Modifier.width(100.dp))

                Text(text =" DETAILS")


            }

        }
    }) {

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {

            Column(modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top) {

                //    //Text(text ="$movieName DETAILS")
                //        Text(text ="${movieList[0].title} DETAILS")


                MovieRow(movie = movieList[0]){


                }

                Spacer(modifier = Modifier.height(10.dp))

                Divider()
                Text(text = "Movie Images")

                HorizontalScrollableImages(movieList)


            }
        }



    }


}

@Composable
private fun HorizontalScrollableImages(movieList: List<Movie>) {
    LazyRow {
        items(movieList[0].images) { movieImg ->

            Card(
                modifier = Modifier
                    .padding(15.dp)
                    .size(250.dp),
                elevation = 5.dp
            ) {

                Image(
                    painter = rememberImagePainter(data = movieImg),
                    contentDescription = "Poster"
                )

            }

        }

    }
}