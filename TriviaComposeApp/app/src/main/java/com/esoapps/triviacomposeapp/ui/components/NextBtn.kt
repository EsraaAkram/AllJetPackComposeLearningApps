package com.esoapps.triviacomposeapp.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.esoapps.triviacomposeapp.util.AppColors


@Composable
fun NextButton(onNextClicked:()-> Unit) {

    Column {
        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp)
            .align(alignment = Alignment.CenterHorizontally),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = AppColors.mLightBlue,

                ),

            onClick = {
                onNextClicked.invoke()
            }) {

            Text(
                text = "Next", modifier = Modifier.padding(5.dp),
                color = AppColors.mOffWhite, fontSize = 18.sp
            )

        }
    }

}
