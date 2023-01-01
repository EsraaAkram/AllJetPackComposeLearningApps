package com.esoapps.weathercomposeapp.ui.screens.main

import androidx.lifecycle.ViewModel
import com.esoapps.weathercomposeapp.data.DataOrException
import com.esoapps.weathercomposeapp.model.Weather
import com.esoapps.weathercomposeapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {

    suspend fun getWeatherData(
        cityQuery: String,
        unitQuery: String
    ): DataOrException<Weather, Boolean, Exception> {

        return weatherRepository.getWeatherApi(
            queryCity = cityQuery,
            unitQuery = unitQuery
        )
    }


//        val data:MutableState<DataOrException<Weather,Boolean,Exception>> =
//            mutableStateOf(DataOrException(null,true,Exception("")))
//
//
//
//    init {
//
//        loadWeatherData()
//    }
//
//    fun loadWeatherData() {
//
//        getWeather("cairo")//Seattle
//    }
//
//    private fun getWeather(cityQuery:String) {
//        viewModelScope.launch{
//
//            if (cityQuery.isEmpty())return@launch
//
//
//            data.value.loading=true
//            data.value= weatherRepository.getWeatherApi(cityQuery)
//
//            if (data.value.data.toString().isNotEmpty()) {
//                data.value.loading = false
//
//                Log.d("getWeatherApi","ViewModel: ${data.value.data}")
//
//            }
//        }
//
//
//    }


}