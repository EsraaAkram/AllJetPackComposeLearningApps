package com.esoapps.triviacomposeapp.ui.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esoapps.triviacomposeapp.data.DataOrException
import com.esoapps.triviacomposeapp.model.QuestionItem
import com.esoapps.triviacomposeapp.repository.QuestionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuestionViewModel @Inject constructor(
    private val questionRepository: QuestionRepository
) : ViewModel() {

    val dataOrException: MutableState<DataOrException<ArrayList<QuestionItem>, Boolean, Exception>> =
        mutableStateOf(
            DataOrException(
                null,
                true,
                Exception(" ")
            )

        )


    init {
        getAllQuestions()
    }

    private fun getAllQuestions() {

        viewModelScope.launch {

            dataOrException.value.loading = true
            dataOrException.value = questionRepository.getAllQuestions()


            if (dataOrException.value.data.toString().isNotEmpty()){
                dataOrException.value.loading=false

            }
        }
    }

    fun getTotalQuestionCount(): Int {
        return dataOrException.value.data?.toMutableList()?.size ?: 0
    }


}