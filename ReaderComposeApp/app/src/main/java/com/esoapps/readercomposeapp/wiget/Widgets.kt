package com.esoapps.readercomposeapp.wiget

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun LogoText(modifier: Modifier = Modifier) {
    Text(
        modifier=modifier.padding(15.dp),
        text = "A.Reader App",
        style = MaterialTheme.typography.h4,
        color = Color.Red.copy(.5F),


        )
}

@Composable
fun EmailInputFields(
    modifier: Modifier = Modifier,
    emailState: MutableState<String>,
    labelId: String = "Email",
    enabled: Boolean = true,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,


    ) {


    InputField(
        modifier = modifier,
        valueState = emailState,
        labelId = labelId,
        enabled = enabled,
        isSingleLine = true,
        keyboardType = KeyboardType.Email,
        imeAction = imeAction,
        onAction = onAction,

        )

}



@Composable
fun InputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    labelId: String,
    enabled: Boolean = true,

    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,

    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
) {


    OutlinedTextField(
        modifier = modifier
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
            .fillMaxWidth(),
        value = valueState.value,
        onValueChange = {
            valueState.value = it
        },
        label = { Text(text = labelId) },
        singleLine = isSingleLine,
        textStyle = TextStyle(
            fontSize = 18.sp,
            color = MaterialTheme.colors.onBackground,
        ),
        enabled = enabled,

        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,

        )

}



@Composable
fun PasswordInputFields(
    modifier: Modifier,
    passwordState: MutableState<String>,
    enabled: Boolean,
    labelId: String,
    passwordVisibility: MutableState<Boolean>,
    imeAction: ImeAction = ImeAction.None,
    onAction: KeyboardActions = KeyboardActions.Default,

    ) {

    val visualTransformation = if (passwordVisibility.value) {
        VisualTransformation.None
    } else {
        PasswordVisualTransformation()
    }

    OutlinedTextField(
        modifier = modifier.padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
            .fillMaxWidth(),

        value = passwordState.value,
        onValueChange = {
            passwordState.value = it
        },
        label = { Text(text = labelId) },
        singleLine = true,
        textStyle = TextStyle(
            fontSize = 18.sp,
            color = MaterialTheme.colors.onBackground,
        ),
        enabled = enabled,

        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password, imeAction = imeAction
        ),
        keyboardActions = onAction,
        visualTransformation = visualTransformation,
        trailingIcon = {
            PasswordVisibility(passwordVisibility = passwordVisibility)
        },


        )
}

@Composable
fun PasswordVisibility(passwordVisibility: MutableState<Boolean>) {

    val visible = passwordVisibility.value
    IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) {

        Icons.Default.Close

    }
}




@Composable
fun SubmitButton(
    textId: String,
    isLoading: Boolean,
    validInputs: Boolean,
    onClick: () -> Unit
) {

    Button(modifier = Modifier
        .padding(5.dp)
        .fillMaxWidth(),
        enabled = !isLoading&&validInputs,
        shape = CircleShape,

        onClick = { onClick()}) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.size(25.dp))
        }else {
            Text(modifier = Modifier.padding(5.dp), text = textId)
        }

    }
}

