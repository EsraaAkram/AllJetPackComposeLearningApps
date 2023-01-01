package com.esoapps.triviacomposeapp.ui.views

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.esoapps.triviacomposeapp.ui.screens.QuestionViewModel
import com.esoapps.triviacomposeapp.ui.components.QuestionDisplay
import com.esoapps.triviacomposeapp.ui.widgets.LoadingView


@Composable
fun QuestionsView(questionViewModel: QuestionViewModel) {


    var questionIndex = remember {
        mutableStateOf(0)
    }

    val questionsList = questionViewModel.dataOrException.value.data?.toMutableList()

    if (questionViewModel.dataOrException.value.loading == true) {

        LoadingView()

    } else {

        val currentQuestion = try {
            questionsList?.get(questionIndex.value)
        } catch (ex: Exception) {
            null
        }



        if (questionsList != null) {

            Log.d("questionItem", "questionItem" + questionsList.size)

            if (currentQuestion != null) {

                QuestionDisplay(
                    questionViewModel = questionViewModel,
                    question = currentQuestion,
                    questionIndex = questionIndex,
                ) {

                    if (questionIndex.value < questionsList.size) {

                        questionIndex.value = questionIndex.value + 1

                    } else {//DONE ALL QUESTIONS



                    }

                }
            }
        }

    }
}



