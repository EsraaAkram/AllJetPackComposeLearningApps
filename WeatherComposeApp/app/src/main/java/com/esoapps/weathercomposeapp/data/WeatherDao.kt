package com.esoapps.weathercomposeapp.data

import androidx.room.*
import com.esoapps.weathercomposeapp.model.Favourite
import com.esoapps.weathercomposeapp.model.Units
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query(value = "SELECT * FROM fav_tbl")
    fun getAllFavList(): Flow<List<Favourite>>


    @Query("SELECT * FROM fav_tbl where city=:city")
    suspend fun getFavCityById(city: String): Favourite


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavouriteCity(favourite: Favourite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavCity(favourite: Favourite)


    @Query("DELETE FROM fav_tbl")
    suspend fun deleteAllFavCities()

    @Delete
    suspend fun deleteFavCity(favourite: Favourite)



    @Query(value = "SELECT * FROM settings_tbl")
    fun getAllUnitsList(): Flow<List<Units>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnit(units: Units)


    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUnit(units: Units)


    @Query("DELETE FROM settings_tbl")
    suspend fun deleteAllUnits()


    @Delete
    suspend fun deleteUnit(units: Units)

}