package com.esoapps.weathercomposeapp.ui.screens.search

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.esoapps.weathercomposeapp.navigation.WeatherScreens
import com.esoapps.weathercomposeapp.ui.widgets.WeatherAppToolBar

@Composable
fun SearchScreen(
    navController: NavController,


    ) {


    Scaffold(topBar = {
        WeatherAppToolBar(
            title = "Search",
            icon = Icons.Default.ArrowBack,
            isFromMain = false,

            navController = navController,
        ) {
            navController.popBackStack()

        }
    }) {
        Surface {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {

                SearchBar(
                    modifier=Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .align(Alignment.CenterHorizontally),
                ){value->

                    Log.d("cityFromSearch",value)

                    navController.navigate(
                        WeatherScreens.MAIN_SCREEN.name+"/${value}")
                }

            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchBar(
    modifier: Modifier =Modifier,
    onSearch: (String) -> Unit,
) {
    val searchQueryState = rememberSaveable {
        mutableStateOf("")
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(searchQueryState.value) {
        searchQueryState.value.trim().isNotEmpty()
    }


    Column {
        CommonTextField(
            valueState = searchQueryState,
            placerHolder = "cairo",
            onAction = KeyboardActions {
                if (!valid) {
                    return@KeyboardActions
                }else{
                    onSearch(searchQueryState.value.trim())
                    searchQueryState.value=""
                    keyboardController?.hide()
                }

            })
    }

}


@Composable
fun CommonTextField(
    valueState: MutableState<String>,
    placerHolder: String,
    onAction: KeyboardActions = KeyboardActions.Default,
    keyBoardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next
) {


    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 10.dp,
                end = 10.dp
            ),
        value = valueState.value,
        onValueChange = {
            valueState.value = it
        },
        label = { Text(text = placerHolder) },
        maxLines = 1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyBoardType),
        keyboardActions = onAction,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue,
            cursorColor = Color.Black,
        ),
        shape = RoundedCornerShape(15.dp),
    )
}