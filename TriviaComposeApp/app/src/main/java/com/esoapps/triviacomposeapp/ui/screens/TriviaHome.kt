package com.esoapps.triviacomposeapp.ui.screens

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.esoapps.triviacomposeapp.ui.views.QuestionsView


@Composable
fun TriviaHomeApp(
    questionViewModel: QuestionViewModel = hiltViewModel()
) {

    QuestionsView(questionViewModel)

}