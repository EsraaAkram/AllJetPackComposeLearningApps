package com.esoapps.notecomposeapp.widgets

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.esoapps.notecomposeapp.R


@Composable
fun NoteTopAppBar() {
    TopAppBar(modifier = Modifier
        .padding(
            bottom = 5.dp
        ),
        title = {
            Text(stringResource(id = R.string.app_name))
        },

        backgroundColor = MaterialTheme.colors.primary,
        actions = {
            Icon(
                imageVector = Icons.Filled.Create,
                contentDescription = "Icon",
                tint = Color.White,
            )
        })
}

