package com.esoapps.weathercomposeapp.ui.screens.settings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esoapps.weathercomposeapp.model.Units
import com.esoapps.weathercomposeapp.repository.WeatherFavRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel  @Inject constructor(
    private val weatherFavRepository: WeatherFavRepository,
) : ViewModel(){


    private val _unitsList = MutableStateFlow<List<Units>>(emptyList())
    val unitsList =_unitsList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {

            weatherFavRepository.getAllUnitsList()
                .distinctUntilChanged()
                .collect{ listOfUnits ->
                    if (listOfUnits.isNullOrEmpty()) {

                        Log.d("weatherFavRepository", "empty")
                        _unitsList.value = emptyList()
                    } else {

                        _unitsList.value = listOfUnits

                        Log.d("weatherFavRepository", "${unitsList.value}")

                    }

                }
        }

    }




    fun insertUnit(units: Units) {
        viewModelScope.launch {
            weatherFavRepository.insertUnit(units)
        }
    }


    fun updateUnit(units: Units) {
        viewModelScope.launch {
            weatherFavRepository.updateUnit(units)
        }
    }


    fun deleteAllUnit() {
        viewModelScope.launch {

            weatherFavRepository.deleteAllUnits()
        }
    }


    fun deleteUnit(units: Units) {
        viewModelScope.launch {

            weatherFavRepository.deleteUnit(units)
        }
    }


}