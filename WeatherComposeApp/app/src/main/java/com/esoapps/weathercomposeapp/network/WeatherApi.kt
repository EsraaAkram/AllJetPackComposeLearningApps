package com.esoapps.weathercomposeapp.network

import com.esoapps.weathercomposeapp.model.Weather
import com.esoapps.weathercomposeapp.utils.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface WeatherApi {

    @GET(value = "data/2.5/forecast/daily")
    suspend fun getWeatherApi(
        @Query("q") queryCity: String,//CITY
        @Query("units") unitQuery: String = "imperial",
        @Query("appid") appid: String = API_KEY

    ): Weather
}

