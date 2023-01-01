package com.esoapps.weathercomposeapp.ui.screens.settings

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.esoapps.weathercomposeapp.model.Units
import com.esoapps.weathercomposeapp.ui.widgets.WeatherAppToolBar

@Composable
fun SettingScreen(
    navController: NavHostController,
    settingsViewModel: SettingsViewModel = hiltViewModel(),
) {
    var unitToggleState = remember {
        mutableStateOf(false)
    }

    val measurementUnit = listOf(
        "Imperial (F)",
        "Metric (C)"
    )

    val choiceFromDb = settingsViewModel.unitsList.collectAsState().value


    var defaultChoice = if (choiceFromDb.isNullOrEmpty()) {

        measurementUnit[0]
    }else{
        choiceFromDb[0].unit
    }


    var chooseState = remember {
        //mutableStateOf("")
        mutableStateOf(defaultChoice)
    }
    Log.d("defaultChoice",defaultChoice)
    Log.d("defaultChoice0",chooseState.value)

    Scaffold(
        topBar = {
            WeatherAppToolBar(
                title = "settings",
                icon = Icons.Default.ArrowBack,
                isFromMain = false,
                navController = navController,
            ){
                navController.popBackStack()
            }
        }) {paddingValuse->

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                Text(
                    modifier = Modifier.padding(15.dp),
                    text = "Change Unit Measurement"
                )


                IconToggleButton(modifier = Modifier
                    .fillMaxWidth(fraction = 0.5F)
                    .clip(shape = RectangleShape)
                    .padding(5.dp)
                    .background(Color.Magenta.copy(alpha = 0.4F)),

                    checked = !unitToggleState.value,
                    onCheckedChange = {

                        unitToggleState.value = !it

                        if (!unitToggleState.value) {
                            chooseState.value = "Imperial (F)"
                        } else {
                            chooseState.value = "Metric (C)"


                        }

                    }) {

                    Text(
                        text = if (unitToggleState.value) {

                            "(F)"
                        } else {
                            "(C)"


                        }
                    )


                }


                Button(modifier = Modifier
                    .padding(5.dp)
                    .align(CenterHorizontally),
                    shape = RoundedCornerShape(35.dp),
                    colors = ButtonDefaults.buttonColors(

                    ),
                    onClick = {

                        settingsViewModel.deleteAllUnit()

                        settingsViewModel.insertUnit(Units(unit = chooseState.value))

                        navController.popBackStack()

                    }) {

                    Text(
                        modifier = Modifier
                            .padding(5.dp), text = "Save"
                    )

                }

            }


        }

    }


}