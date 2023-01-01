package com.esoapps.weathercomposeapp.repository

import android.util.Log
import com.esoapps.weathercomposeapp.data.DataOrException
import com.esoapps.weathercomposeapp.model.Weather
import com.esoapps.weathercomposeapp.network.WeatherApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class WeatherRepository @Inject constructor(private val weatherApi: WeatherApi){

    suspend fun getWeatherApi(queryCity: String,
                              unitQuery: String):DataOrException<Weather,Boolean,Exception>{

        val response=try {
            weatherApi.getWeatherApi(queryCity=queryCity,
                unitQuery=unitQuery)

        }catch (e:Exception){
            Log.d("getWeatherApi","Repo: ${e.toString()} ${e.message} ${e.cause} ${e.stackTrace}")
            return DataOrException(e=e)
        }

        return DataOrException(data = response)
    }
}