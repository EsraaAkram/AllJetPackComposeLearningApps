package com.esoapps.mymoviescomposeapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.esoapps.mymoviescomposeapp.model.Movie
import com.esoapps.mymoviescomposeapp.navigation.MovieNavigation
import com.esoapps.mymoviescomposeapp.ui.theme.MyMoviesComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            MyApp {
                //MyMainContent()
                MovieNavigation()
            }
        }
    }
}

@Composable
fun MyApp(content:@Composable()()->Unit) {
    MyMoviesComposeAppTheme {

        androidx.compose.material.Surface(
            color = MaterialTheme.colors.background
        ) {


            content()

        }

    }
}





@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
   MyApp {
       //MyMainContent()
       MovieNavigation()
   }
}