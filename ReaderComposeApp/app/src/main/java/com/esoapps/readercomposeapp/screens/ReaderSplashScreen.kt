package com.esoapps.readercomposeapp.screens

import android.util.Log
import android.view.animation.OvershootInterpolator
import androidx.compose.animation.Animatable
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.tween
import androidx.compose.ui.draw.scale
import com.esoapps.readercomposeapp.navigations.ReaderScreens
import com.esoapps.readercomposeapp.wiget.LogoText
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.delay

@Preview
@Composable
fun ReaderSplashScreen(
    navController: NavController = NavController(LocalContext.current),
) {

    val scale = remember {

        Animatable(0F)
    }

    LaunchedEffect(key1 = true,
        block = {
            scale.animateTo(
                targetValue = 0.9F,
                animationSpec = tween(
                    delayMillis = 1000,
                    easing = Easing {
                        OvershootInterpolator(8f)
                            .getInterpolation(it)
                    }
                ))

            delay(2000)

            //navController.navigate(ReaderScreens.LoginScreen.name)

            var auth=Firebase.auth
            if (!auth.currentUser?.email.isNullOrEmpty()){
                Log.d("User","${auth.currentUser?.email}")
                navController.navigate(ReaderScreens.ReaderHomeScreen.name)
            }else{
                Log.d("User","auth is Empty")

                navController.navigate(ReaderScreens.LoginScreen.name)
            }

        })
    Surface(
        modifier = Modifier
            .padding(15.dp)
            .size(350.dp)
            .scale(scale.value),
        shape = CircleShape,
        color = Color.White,
        border = BorderStroke(
            3.dp,
            color = Color.LightGray
        )
    ) {


        Column(
            modifier = Modifier.padding(1.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            LogoText()

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "\"Read Change Your Self\"",
                style = MaterialTheme.typography.h5,
                color = Color.LightGray.copy(.5F),

                )
        }
    }

}

