package com.esoapps.mymoviescomposeapp.widgets

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.esoapps.mymoviescomposeapp.model.Movie
import com.esoapps.mymoviescomposeapp.model.getMovies
import com.esoapps.mymoviescomposeapp.screens.MovieScreens


@Composable
fun MyMainContent(navController: NavController,
    /*
    moviesList:List<String>
    = listOf(
"Kill Bill v1",
"Kill Bill v2",
"Kill Bill v3",

"GodFather V1",
"GodFather V2",
"GodFather V3",
"Avatar",

"Harry Potter v1",
"Harry Potter v2",
"Harry Potter v3",
"Harry Potter v4",
"What Ever",

)
*/

                  moviesList:List<Movie> = getMovies()
) {


    Column(modifier = Modifier.padding(10.dp)) {
        LazyColumn(){

            items(moviesList){

                //Text(text = it)
                MovieRow(movie = it){clickedMovie->
                    Log.d("clickedMovie",clickedMovie)


                    navController.navigate(
                        route = MovieScreens.DetailsScreen.name+"/$clickedMovie"
                    )
                }

            }

        }
    }

}


@Preview
@Composable
fun MovieRow(
    //movie:String,
    movie:Movie= getMovies()[0],
    clicked:(String)->Unit={}//OPTIONAL
) {

    var expanded by remember {
        mutableStateOf(false)
    }

    Card(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth()
            //.height(150.dp)


            .clickable {
                //clicked(movie)
                clicked(movie.id)
            }
        ,


        shape = RoundedCornerShape(CornerSize(15.dp)),
        elevation = 5.dp
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start) {

            Surface(
                modifier = Modifier
                    .padding(10.dp)
                    .size(100.dp),

                shape = RectangleShape,
                elevation = 5.dp) {

//                Icon(
//                    imageVector = Icons.Default.AccountBox,
//                    contentDescription = "Movie Icon")


                Image(
                    painter = rememberImagePainter(
                        data = movie.images[0],
                    builder={
                        crossfade(true)
                        transformations(CircleCropTransformation())
                    }),
                    contentDescription = "Poster")

            }


            Column(modifier = Modifier.padding(5.dp)) {
                Text(text = movie.title,
                    style=MaterialTheme.typography.h6)

                Text(text = "Director: ${movie.director}",
                    style=MaterialTheme.typography.caption)

                Text(text = "Release: ${movie.year}",
                    style=MaterialTheme.typography.caption)


                AnimatedVisibility(visible = expanded) {
                    Column(
                        modifier = Modifier.padding(5.dp)) {

                        //Text(text = "Hello")

                        Text(modifier=Modifier.padding(5.dp),
                           text= buildAnnotatedString {
                            withStyle(style = SpanStyle(
                                color = Color.Red,
                                fontSize = 12.sp)){
                                append("Plot: ")

                            }
                            withStyle(style = SpanStyle(
                                color = Color.DarkGray,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Light)){

                                append(movie.plot)
                            }
                        })

                        Divider(modifier=Modifier.padding(5.dp))

                        Text(text = "Actors: ${movie.actors}")
                        Text(text = "Rated : ${movie.rating}")
                    }
                }
              

                
                
                Icon(modifier= Modifier
                    .size(25.dp)
                    .clickable {

                        expanded = !expanded

                    },
                    imageVector = if(expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.ArrowDropDown,
                    contentDescription ="EXPAND",
                    tint = Color.Blue)
            }

        }




    }

}
