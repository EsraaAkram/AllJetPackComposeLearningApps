package com.esoapps.triviacomposeapp.ui.widgets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.unit.dp
import com.esoapps.triviacomposeapp.util.AppColors


@Composable
fun DrawDottedLine() {
    val pathEffect = PathEffect.dashPathEffect(
        floatArrayOf(10f, 10f),
        0f
    )
    Canvas(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
    ) {

        this.drawLine(
            color = AppColors.mLightGray,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            pathEffect = pathEffect,
        )


    }

}