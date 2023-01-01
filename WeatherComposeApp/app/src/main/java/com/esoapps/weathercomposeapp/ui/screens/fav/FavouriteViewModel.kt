package com.esoapps.weathercomposeapp.ui.screens.fav

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esoapps.weathercomposeapp.model.Favourite
import com.esoapps.weathercomposeapp.repository.WeatherFavRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val weatherFavRepository: WeatherFavRepository,
) : ViewModel() {


    private val _favList = MutableStateFlow<List<Favourite>>(emptyList())
    val favList = _favList.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {

            weatherFavRepository.getAllFavList().distinctUntilChanged()
                .collect { listOfFavs ->
                    if (listOfFavs.isNullOrEmpty()) {

                        Log.d("weatherFavRepository", "empty")
                        _favList.value = emptyList()
                    } else {

                        _favList.value = listOfFavs

                        Log.d("weatherFavRepository", "${favList.value}")

                    }

                }
        }
    }

    fun insertFavouriteCity(favourite: Favourite) {
        viewModelScope.launch {
            weatherFavRepository.insertFavouriteCity(favourite)
        }
    }

    fun deleteFavCity(favourite: Favourite) {
        viewModelScope.launch {

            weatherFavRepository.deleteFavCity(favourite)
        }
    }

    fun updateFavCity(favourite: Favourite) {
        viewModelScope.launch {
            weatherFavRepository.updateFavCity(favourite)
        }
    }

}