package com.esoapps.weathercomposeapp.ui.screens.main

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.esoapps.weathercomposeapp.data.DataOrException
import com.esoapps.weathercomposeapp.model.Weather
import com.esoapps.weathercomposeapp.navigation.WeatherScreens
import com.esoapps.weathercomposeapp.ui.screens.fav.FavouriteViewModel
import com.esoapps.weathercomposeapp.ui.screens.settings.SettingsViewModel
import com.esoapps.weathercomposeapp.ui.widgets.*
import com.esoapps.weathercomposeapp.utils.formatDate
import com.esoapps.weathercomposeapp.utils.formatDecimals

@Composable
fun MainScreen(
    navController: NavHostController,
    mainViewModel: MainViewModel = hiltViewModel(),

    favouriteViewModel: FavouriteViewModel= hiltViewModel(),
    settingsViewModel: SettingsViewModel= hiltViewModel(),

    city: String?,
) {

    val unitFromDb = settingsViewModel.unitsList.collectAsState().value
    var unit = remember() {

        mutableStateOf("imperial")
    }

    var isImperial = remember {
        mutableStateOf(false)
    }

    if (!unitFromDb.isNullOrEmpty()) {
        unit.value = unitFromDb[0].unit.split(" ")[0].lowercase()
        isImperial.value = unit.value == "imperial "


    }


    var weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true),
    ) {
        value = mainViewModel.getWeatherData(
            cityQuery = city!!,
            unitQuery = unit.value
        )

    }.value

    if (weatherData.loading == true) {

        CircularProgressIndicator()

    } else
        if (weatherData.data != null) {
            Log.d("weatherData", weatherData.data.toString())

            MainScaffold(
                weather = weatherData.data!!,
                navController = navController,
                favouriteViewModel = favouriteViewModel,
                isImperial = isImperial,
            )

        }

}


@Composable
fun MainScaffold(
    weather: Weather,
    navController: NavHostController,
    favouriteViewModel: FavouriteViewModel,
    isImperial: MutableState<Boolean>
) {
    Scaffold(
        topBar = {

            WeatherAppToolBar(
                title = "${weather.city.name},${weather.city.country}",
                navController = navController,
                favouriteViewModel = favouriteViewModel,
                elevation = 5.dp,
                isFromMain = true,
                onAddActionClicked = {
                    navController.navigate(WeatherScreens.SEARCH_SCREEN.name)
                }

            ) {


            }
        },
    ) { paddingValues ->

        MainContent(
            weatherData = weather,
            isImperial = isImperial
        )

    }


}

@Composable
fun MainContent(
    weatherData: Weather,
    isImperial: MutableState<Boolean>
) {

    val weatherItem = weatherData.list[0]
    val imageUrl = "https://openweathermap.org/img/wn/${weatherItem.weather[0].icon}.png"

    Column(
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        Text(
            modifier = Modifier.padding(5.dp),
            text = formatDate(weatherItem.dt), // Wed Nov 30
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSecondary,
        )


        Surface(
            modifier = Modifier
                .padding(5.dp)
                .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC107)
        ) {

            Column(
                modifier = Modifier.padding(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                WeatherStateImg(imageUrl = imageUrl)

                Text(
                    text = formatDecimals(weatherItem.temp.day) + "º",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold
                )

                Text(
                    text = weatherItem.weather[0].main,
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold,
                    fontStyle = FontStyle.Italic
                )


            }


        }


        HumidityWindPressureRow(
            weather = weatherItem,
            isImperial = isImperial
        )

        Divider()

        SunsetSunRiseRow(weather = weatherItem)


        Text(
            "This Week",
            style = MaterialTheme.typography.subtitle1,
            fontWeight = FontWeight.Bold,
        )


        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()

                .padding(5.dp),
            color = Color(0xFFF3F8C2),
            shape = RoundedCornerShape(15.dp)

        ) {

            LazyColumn(
                modifier = Modifier.padding(5.dp),
                contentPadding = PaddingValues(3.dp)
            ) {
                items(items = weatherData.list) { itemWeather ->

                    WeatherDetailsRow(weatherItem = itemWeather)

                }
            }

        }


    }
}

