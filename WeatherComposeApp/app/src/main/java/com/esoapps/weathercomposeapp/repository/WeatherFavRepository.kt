package com.esoapps.weathercomposeapp.repository

import com.esoapps.weathercomposeapp.data.WeatherDao
import com.esoapps.weathercomposeapp.model.Favourite
import com.esoapps.weathercomposeapp.model.Units
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

//@Singleton
class WeatherFavRepository @Inject constructor(
    private val weatherDao: WeatherDao,
){
    fun getAllFavList(): Flow<List<Favourite>> =weatherDao.getAllFavList()
    suspend fun getFavCityById(city:String) =weatherDao.getFavCityById(city)
    suspend fun insertFavouriteCity(favourite: Favourite)=weatherDao.insertFavouriteCity(favourite)
    suspend fun updateFavCity(favourite: Favourite)=weatherDao.updateFavCity(favourite)
    suspend fun deleteAllFavCities()=weatherDao.deleteAllFavCities()
    suspend fun deleteFavCity(favourite: Favourite)=weatherDao.deleteFavCity(favourite)



    fun getAllUnitsList(): Flow<List<Units>> =weatherDao.getAllUnitsList()

    suspend fun insertUnit(units: Units)=weatherDao.insertUnit(units)
    suspend fun updateUnit(units: Units)=weatherDao.updateUnit(units)
    suspend fun deleteAllUnits() =weatherDao.deleteAllUnits()
    suspend fun deleteUnit(units: Units)=weatherDao.deleteUnit(units)



}