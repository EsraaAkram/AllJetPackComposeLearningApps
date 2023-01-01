package com.esoapps.weathercomposeapp.ui.widgets

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.esoapps.weathercomposeapp.R
import com.esoapps.weathercomposeapp.model.Favourite
import com.esoapps.weathercomposeapp.model.WeatherItem
import com.esoapps.weathercomposeapp.navigation.WeatherScreens
import com.esoapps.weathercomposeapp.ui.screens.fav.FavouriteViewModel
import com.esoapps.weathercomposeapp.utils.formatDate
import com.esoapps.weathercomposeapp.utils.formatDateTime
import com.esoapps.weathercomposeapp.utils.formatDecimals

@Composable
fun WeatherAppToolBar(
    title: String = "Weather",
    icon: ImageVector? = null,
    isFromMain: Boolean = true,
    elevation: Dp = 0.dp,

    navController: NavController,

    favouriteViewModel: FavouriteViewModel = hiltViewModel(),

    onAddActionClicked: () -> Unit = {},
    onBtnClicked: () -> Unit = {},

    ) {


    val showDialog = remember {
        mutableStateOf(false)
    }

    if (showDialog.value) {

        ShowSettingDropDownMenu(
            showDialog = showDialog, navController = navController
        )
    }

    val showIt = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    TopAppBar(
        modifier = Modifier,
        backgroundColor = Color.Transparent,
        elevation = elevation,

        title = {
            Text(
                text = title, color = MaterialTheme.colors.onSecondary, style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                )
            )
        },
        actions = {

            if (isFromMain) {
                IconButton(onClick = {
                    onAddActionClicked.invoke()
                }) {
                    Icon(
                        imageVector = Icons.Default.Search, contentDescription = "Search Icon"
                    )

                }

                IconButton(onClick = {
                    //showDialog.value = !showDialog.value
                    showDialog.value = true
                }) {
                    Icon(
                        imageVector = Icons.Default.MoreVert, contentDescription = "More Icon"
                    )

                }
            } else {
                Box() {}
            }

        },
        navigationIcon = {

            if (icon != null) {
                Icon(
                    modifier = Modifier.clickable {

                        onBtnClicked.invoke()
                    },
                    imageVector = icon,
                    contentDescription = "Navigate",
                    tint = MaterialTheme.colors.onSecondary
                )
            }

            if (isFromMain) {


                val isAlreadyInFavList =
                    favouriteViewModel.favList.collectAsState().value.filter { favItem ->
                            (favItem.city == title.split(",")[0])

                        }


                Icon(
                    modifier = Modifier
                        .scale(0.9F)
                        .clickable {

                            if (isAlreadyInFavList.isEmpty()) {
                                favouriteViewModel
                                    .insertFavouriteCity(
                                        Favourite(
                                            title.split(",")[0], title.split(",")[1]
                                        )
                                    )
                                    .run {
                                        showIt.value = true
                                    }
                            } else {
                                favouriteViewModel.deleteFavCity(
                                    Favourite(
                                        title.split(",")[0], title.split(",")[1]
                                    )
                                )
                                showIt.value = true
                            }


                        },
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite Icon",
                    tint =
                    if (isAlreadyInFavList.isEmpty()) Color.Gray.copy(alpha = 0.6F)
                    else Color.Red.copy(alpha = 0.6F)

                )

            }

            ShowToast(context = context, showIt = showIt)


        },

        )


}

@Composable
fun ShowToast(
    context: Context, showIt: MutableState<Boolean>
) {
    if (showIt.value) {
        Toast.makeText(
            context, "Done", Toast.LENGTH_SHORT
        ).show()
    }
    showIt.value = false

}

@Composable
fun ShowSettingDropDownMenu(
    showDialog: MutableState<Boolean>, navController: NavController
) {

    val items = listOf("about", "fav", "settings")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 45.dp, right = 20.dp)
    ) {
        var expanded by remember {
            mutableStateOf(true)
        }

        DropdownMenu(modifier = Modifier
            .width(150.dp)
            .background(Color.White),
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }) {

            items.forEachIndexed { index, text ->
                DropdownMenuItem(onClick = {

                    expanded = false
                    showDialog.value = false

                }) {

                    Icon(
                        imageVector = when (text) {
                            "about" -> Icons.Default.Info
                            "fav" -> Icons.Default.FavoriteBorder
                            "settings" -> Icons.Default.Settings
                            else -> {
                                Icons.Default.Info
                            }
                        },
                        contentDescription = "text",
                        tint = Color.LightGray,
                    )
                    Text(
                        modifier = Modifier.clickable {

                            navController.navigate(
                                when (text) {
                                    "about" -> WeatherScreens.ABOUT_SCREEN.name
                                    "fav" -> WeatherScreens.FAVOURITE_SCREEN.name
                                    "settings" -> WeatherScreens.SETTINGS_SCREEN.name

                                    else -> WeatherScreens.ABOUT_SCREEN.name
                                }
                            )
                        }, text = text, fontWeight = FontWeight.W300
                    )


                }

            }

        }


    }


}


@Composable
fun WeatherStateImg(imageUrl: String) {

    Image(
        modifier = Modifier.size(50.dp),
        painter = rememberImagePainter(imageUrl),
        contentDescription = "Img"
    )

}


@Composable
fun HumidityWindPressureRow(
    weather: WeatherItem, isImperial: MutableState<Boolean>
) {

    Row(
        modifier = Modifier
            .padding(15.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        Row(modifier = Modifier.padding(5.dp)) {
            Icon(
                modifier = Modifier.size(25.dp),
                painter = painterResource(id = R.drawable.humidity),
                contentDescription = "humidity"
            )
            Text(
                text = "${weather.humidity}%", style = MaterialTheme.typography.caption
            )
        }


        Row(modifier = Modifier.padding(5.dp)) {
            Icon(
                modifier = Modifier.size(25.dp),
                painter = painterResource(id = R.drawable.pressure),
                contentDescription = "pressure"
            )
            Text(
                text = "${weather.pressure}psi", style = MaterialTheme.typography.caption
            )
        }

        Row(modifier = Modifier.padding(5.dp)) {
            Icon(
                modifier = Modifier.size(25.dp),
                painter = painterResource(id = R.drawable.wind),
                contentDescription = "wind"
            )
            Text(
                //text = "${weather.speed} mph",
                text = "${formatDecimals(weather.speed)} ${
                    if (isImperial.value == true) {
                        "mph"
                    } else {
                        "m/s"
                    }
                }", style = MaterialTheme.typography.caption
            )
        }

    }
}


@Composable
fun SunsetSunRiseRow(weather: WeatherItem) {


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                top = 15.dp, bottom = 5.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {

        Row {
            Image(
                modifier = Modifier.size(25.dp),
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = "sunrise"
            )
            Text(
                text = formatDateTime(weather.sunrise), style = MaterialTheme.typography.caption
            )
        }




        Row {
            Image(
                modifier = Modifier.size(25.dp),
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = "sunset"
            )
            Text(
                text = formatDateTime(weather.sunset), style = MaterialTheme.typography.caption
            )
        }


    }
}


@Composable
fun WeatherDetailsRow(weatherItem: WeatherItem) {

    val imageUrl = "https://openweathermap.org/img/wn/${weatherItem.weather[0].icon}.png"

    Surface(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        shape = CircleShape.copy(topEnd = CornerSize(5.dp)),
        color = Color.White,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {

            Text(
                modifier = Modifier.padding(5.dp),
                text = formatDate(weatherItem.dt).split(",")[0].toString(),
                style = MaterialTheme.typography.caption,
                fontStyle = FontStyle.Italic,
            )

            WeatherStateImg(imageUrl = imageUrl)

            Surface(
                modifier = Modifier.padding(5.dp), shape = CircleShape, color = Color(0xFFF59F21)
            ) {
                Text(
                    modifier = Modifier.padding(5.dp),
                    text = weatherItem.weather[0].description,
                    style = MaterialTheme.typography.caption
                )

            }


            Text(text = buildAnnotatedString {

                withStyle(
                    style = SpanStyle(
                        color = Color.Blue.copy(
                            alpha = 0.7f
                        ), fontWeight = FontWeight.SemiBold, fontSize = 10.sp
                    )
                ) {
                    append("${formatDecimals(weatherItem.temp.max)}D")
                }
                withStyle(
                    style = SpanStyle(
                        color = Color.LightGray, fontSize = 14.sp
                    )
                ) {
                    append("${formatDecimals(weatherItem.temp.min)}D")
                }
            })


        }


    }


}