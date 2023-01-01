package com.esoapps.weathercomposeapp.ui.screens.splash

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.esoapps.weathercomposeapp.R
import com.esoapps.weathercomposeapp.navigation.WeatherNavigation
import com.esoapps.weathercomposeapp.navigation.WeatherScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
) {

    val defaultCity = "cairo"

    val scale = remember {
        Animatable(0F)
    }

    LaunchedEffect(key1 = true,
        block = {
            scale.animateTo(
                targetValue = .9F,
                animationSpec = tween(
                    durationMillis = 1000,
                    easing = {
                        OvershootInterpolator(8f)
                            .getInterpolation(it)
                    }
                ),
            )
            delay(1000L)//2000L

            navController.navigate(
                WeatherScreens.MAIN_SCREEN.name
                        + "/${defaultCity}"
            ) {
                popUpTo(0)
            }

        }

    )


    Surface(
        modifier = Modifier
            .padding(15.dp)
            .size(350.dp)
            .scale(scale.value),

        shape = CircleShape,
        color = Color.White,
        border = BorderStroke(
            width = 1.dp,
            color = Color.LightGray
        )


    ) {

        Column(
            modifier = Modifier.padding(1.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Image(
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "App Icon",
                contentScale = ContentScale.Fit,

                modifier = Modifier.size(100.dp),
            )

            Text(
                text = "Find The Sun?", color = Color.LightGray,
                style = MaterialTheme.typography.h5
            )

        }


    }

}