package com.esoapps.notecomposeapp.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp

@ExperimentalComposeUiApi
@Composable
fun NoteInputTxt(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    maxLines: Int = 1,
    onTxtChange: (String) -> Unit,
    onImeAction: () -> Unit = {},//OPTIONAL

) {

    val keyBoardController = LocalSoftwareKeyboardController.current

    TextField(
        value = text,
        onValueChange = onTxtChange,
        label = { Text(text = label) },

        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent
        ),
        maxLines = maxLines,


        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(onDone = {
            onImeAction()
            keyBoardController?.hide()

        }),
        modifier = modifier
    )


}


@Composable
fun NoteSaveBtn(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true
) {

    Button(
        modifier = modifier,
        onClick = onClick,
        shape = CircleShape.copy(all = CornerSize(5.dp)),
        enabled = enabled
    ) {

        Text(text = text)

    }
}