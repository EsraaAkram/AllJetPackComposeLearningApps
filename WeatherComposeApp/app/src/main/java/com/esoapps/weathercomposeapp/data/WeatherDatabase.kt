package com.esoapps.weathercomposeapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.esoapps.weathercomposeapp.model.Favourite
import com.esoapps.weathercomposeapp.model.Units

@Database(entities = [Favourite::class,Units::class],
    version = 2,
    exportSchema = false)
abstract class WeatherDatabase:RoomDatabase(){

    abstract fun weatherDao():WeatherDao

}