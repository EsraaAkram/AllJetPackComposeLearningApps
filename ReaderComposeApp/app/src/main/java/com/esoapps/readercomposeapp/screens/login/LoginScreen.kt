package com.esoapps.readercomposeapp.screens.login

import android.util.Log
import android.view.FocusFinder
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.esoapps.readercomposeapp.navigations.ReaderScreens
import com.esoapps.readercomposeapp.wiget.*

@Composable
fun LoginScreen(navController: NavHostController,
                loginViewModel: LoginViewModel= viewModel()) {

    var showLoginForm = rememberSaveable {
        mutableStateOf(true)

    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            LogoText()

            if (showLoginForm.value) {
                UserForm(
                    isLoading = false,
                    isCreateAccount = false
                ) { email, password ->

                    Log.d("LoginScreen", "Login: $email $password")

                    loginViewModel.signInByEmailAndPassword(email=email,password=password){

                        navController.navigate(ReaderScreens.ReaderHomeScreen.name)
                    }


                }
            } else {
                UserForm(
                    isLoading = false,
                    isCreateAccount = true
                ) { email, password ->

                    Log.d("LoginScreen", "Sign Up: $email $password")


                    loginViewModel.createUserByEmailAndPassword(email=email,password=password){

                        navController.navigate(ReaderScreens.ReaderHomeScreen.name)
                    }
                }
            }


        }

        Spacer(modifier = Modifier.height(50.dp))


        Row(
            modifier = Modifier
                .padding(25.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val text = if (showLoginForm.value) {
                "Create Account From Here"
            } else {
                "Log in From Here"
            }

            Text(modifier = Modifier
                .padding(25.dp)
                .clickable {

                    showLoginForm.value = !showLoginForm.value

                }, text = text
            )
        }
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun UserForm(
    isLoading: Boolean = false,
    isCreateAccount: Boolean = false,
    onDone: (String, String) -> Unit

) {

    var email = rememberSaveable {
        mutableStateOf("")
    }

    var password = rememberSaveable() {
        mutableStateOf("")
    }

    var passwordVisibility = rememberSaveable() {

        mutableStateOf(false)
    }


    var passwordFocusRequest = FocusRequester.Default
    var keyboardController = LocalSoftwareKeyboardController.current

    var valid = remember(email.value, password.value) {

        email.value.trim().isNotEmpty() && password.value.trim().isNotEmpty()
    }

    val modifier = Modifier
        .height(250.dp)
        .background(MaterialTheme.colors.background)
        .verticalScroll(rememberScrollState())


    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (isCreateAccount) {
            Text(text = "Create Account")
        } else {
            Text(text = "WelcomeBack")
        }

        EmailInputFields(
            modifier = Modifier,
            emailState = email,
            enabled = !isLoading,
            labelId = "Email",
            onAction = KeyboardActions {
                passwordFocusRequest.requestFocus()
            })


        PasswordInputFields(
            modifier = Modifier.focusRequester(passwordFocusRequest),
            passwordState = password,
            enabled = !isLoading,
            labelId = "Password",
            passwordVisibility = passwordVisibility,
            onAction = KeyboardActions {
                if (!valid) return@KeyboardActions
                onDone(email.value.trim(), password.value.trim())
            },

            )

        SubmitButton(
            textId = if (isCreateAccount) {
                "Create"
            } else {
                "Login"
            }, isLoading = isLoading, validInputs = valid
        ) {
            keyboardController?.hide()

            onDone(email.value, password.value)
        }


    }

}


