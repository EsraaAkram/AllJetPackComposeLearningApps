package com.esoapps.triviacomposeapp.network

import com.esoapps.triviacomposeapp.model.QuestionsList
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface QuestionApi {

    @GET("world.json")
    suspend fun getAllQuestions(): QuestionsList


}