package com.esoapps.triviacomposeapp.di

import com.esoapps.triviacomposeapp.network.QuestionApi
import com.esoapps.triviacomposeapp.repository.QuestionRepository
import com.esoapps.triviacomposeapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideQuestionApi(): QuestionApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(QuestionApi::class.java)


    @Singleton
    @Provides
    fun provideQuestionRepository(questionApi: QuestionApi) =
        QuestionRepository(questionApi)

}