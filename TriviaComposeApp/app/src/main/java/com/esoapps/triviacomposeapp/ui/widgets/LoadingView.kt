package com.esoapps.triviacomposeapp.ui.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.esoapps.triviacomposeapp.util.AppColors


@Composable
fun LoadingView() {
    Column(
        modifier = Modifier
            .background(AppColors.mDarkPurple),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        CircularProgressIndicator(
            modifier = Modifier
                .padding(15.dp),
            color = AppColors.mOffWhite
        )

    }
}
