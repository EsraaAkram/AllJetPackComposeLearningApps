package com.esoapps.readercomposeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.esoapps.readercomposeapp.navigations.ReaderNavigation
import com.esoapps.readercomposeapp.ui.theme.ReaderComposeAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ReaderComposeAppTheme {
                ReaderApp()
            }


        }
    }
}


@Composable
fun ReaderApp() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {

        ReaderNavigation()

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ReaderComposeAppTheme {

    }
}