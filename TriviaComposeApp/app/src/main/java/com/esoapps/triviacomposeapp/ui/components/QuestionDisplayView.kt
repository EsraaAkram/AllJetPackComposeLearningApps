package com.esoapps.triviacomposeapp.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.esoapps.triviacomposeapp.model.QuestionItem
import com.esoapps.triviacomposeapp.ui.screens.QuestionViewModel
import com.esoapps.triviacomposeapp.util.AppColors
import com.esoapps.triviacomposeapp.ui.widgets.DrawDottedLine
import com.esoapps.triviacomposeapp.widgets.NextButton
import com.esoapps.triviacomposeapp.ui.widgets.QuestionTracker
import com.esoapps.triviacomposeapp.ui.widgets.ShowProgress

@Composable
fun QuestionDisplay(
    //showSystemUi: Boolean = true,
    showBackground: Boolean = true,
    questionViewModel: QuestionViewModel,
    question: QuestionItem,
    questionIndex: MutableState<Int>,
    onNextClicked: (Int) -> Unit,
) {

    val choicesState = remember(question) {
        question.choices.toMutableList()
    }

    val answerState = remember(question) {
        mutableStateOf<Int?>(null)
    }
    val correctOrNotAnswerState = remember(question) {
        mutableStateOf<Boolean?>(null)
    }

    //CHECK THE SELECTED RADIO BTN (MAKE IT SELECTED)
    //TAKING THE USER ANSWER INDEX AND CHECK IT:
    val updateAnswer: (Int) -> Unit = remember(question) {
        {userChoiceIndex->
            answerState.value = userChoiceIndex


            correctOrNotAnswerState.value =
                choicesState[userChoiceIndex] == question.answer

        }
    }

    val currentEnableState=  remember {
        mutableStateOf<Boolean?>(null)
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxWidth(),

        color = AppColors.mDarkPurple
    ) {

        Column(
            modifier = Modifier
                .padding(12.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {

            ShowProgress(questionIndex.value)

            QuestionTracker(
                counter = questionIndex.value,
                outOf = questionViewModel.getTotalQuestionCount()
            )

            DrawDottedLine()

            Column {

                QuestionTitleView(question)


                ChoicesView(
                    choicesState = choicesState,
                    answerState = answerState,
                    correctOrNotAnswerState = correctOrNotAnswerState,
                    currentEnableState=currentEnableState
                ) { userChoiceIndex ->
                    if (userChoiceIndex != null) {
                        updateAnswer(userChoiceIndex)

                        //STOP USER FROM RE-CHOOSE:
                        currentEnableState.value=false

                    }
                }

                NextButton {
                    //MAKE USER ABLE TO CHOOSE AGAIN:
                    currentEnableState.value=true

                    onNextClicked(questionIndex.value)
                }
            }

        }

    }

}
