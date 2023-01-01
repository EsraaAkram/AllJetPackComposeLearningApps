package com.esoapps.triviacomposeapp.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.esoapps.triviacomposeapp.util.AppColors

@Composable
fun ChoicesView(
    choicesState: MutableList<String>,
    answerState: MutableState<Int?>,
    correctOrNotAnswerState: MutableState<Boolean?>,
    currentEnableState: MutableState<Boolean?>,

    updateAnswer: (Int?) -> Unit,
) {
    choicesState.forEachIndexed { index, answerText ->

        Row(
            modifier = Modifier
                .clickable {
                    if (currentEnableState.value != false){//FIRST TIME CLICK
                        Log.d("answerState", answerState.value.toString())
                        updateAnswer(index)

                    }

                }
                .padding(3.dp)
                .fillMaxWidth()
                .height(44.dp)
                .border(
                    width = 4.dp, brush = Brush.linearGradient(

                        colors = listOf(
                            AppColors.mOffDarkPurple,
                            AppColors.mOffDarkPurple
                        )
                    ),
                    shape = RoundedCornerShape(15.dp)

                )
                .clip(
                    RoundedCornerShape(
                        topStartPercent = 50,
                        topEndPercent = 50,
                        bottomStartPercent = 50,
                        bottomEndPercent = 50,
                    )
                )
                .background(Color.Transparent),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            RadioButton(

                selected = (answerState.value == index),
                onClick = {
                    Log.d("answerState", answerState.value.toString())
                    updateAnswer(index)

                },
                modifier = Modifier.padding(start = 16.dp),
                colors = RadioButtonDefaults.colors(
                    selectedColor = if (
                        correctOrNotAnswerState.value == true &&
                        index == answerState.value
                    ) {
                        Color.Green.copy(alpha = .2f)
                    } else {
                        Color.Red.copy(alpha = .2f)

                    }
                ),
                enabled = currentEnableState.value ?: true,
            )

            val annotateString = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        fontWeight = FontWeight.Light,
                        color = if (
                            correctOrNotAnswerState.value == true
                            &&
                            index == answerState.value
                        ) {
                            Color.Green
                        } else if (correctOrNotAnswerState.value == false
                            &&
                            index == answerState.value
                        ) {
                            Color.Red

                        } else {
                            AppColors.mOffWhite

                        }, fontSize = 17.sp
                    )
                ) {
                    append(answerText)
                }
            }

            Text(
                text = annotateString,
                modifier = Modifier.padding(5.dp)
            )

        }

    }
}