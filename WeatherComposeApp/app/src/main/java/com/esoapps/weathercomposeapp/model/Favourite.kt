package com.esoapps.weathercomposeapp.model

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_tbl")
data class Favourite(

    //@NonNull
    @PrimaryKey
    @ColumnInfo(name = "city")
    var city: String,

    @ColumnInfo(name = "country")
    var country: String
)
