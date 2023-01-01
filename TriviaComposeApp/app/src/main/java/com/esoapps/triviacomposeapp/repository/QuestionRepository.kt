package com.esoapps.triviacomposeapp.repository

import com.esoapps.triviacomposeapp.data.DataOrException
import com.esoapps.triviacomposeapp.model.QuestionItem
import com.esoapps.triviacomposeapp.network.QuestionApi
import javax.inject.Inject


class QuestionRepository @Inject constructor(
    private val questionApi: QuestionApi,
) {

    private val dataOrException = DataOrException<ArrayList<QuestionItem>, Boolean, Exception>()

    suspend fun getAllQuestions(): DataOrException<ArrayList<QuestionItem>, Boolean, Exception> {

        try {

            dataOrException.loading = true
            dataOrException.data = questionApi.getAllQuestions()

            if (dataOrException.data.toString().isNotEmpty()) {

                dataOrException.loading = false
            }

        } catch (e: Exception) {

            dataOrException.exception = e

        }

        return dataOrException
    }
}