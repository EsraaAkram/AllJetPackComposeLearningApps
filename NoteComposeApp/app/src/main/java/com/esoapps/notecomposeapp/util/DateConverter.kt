package com.esoapps.notecomposeapp.util

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun fromDateToLong(date: Date):Long=
        date.time

    @TypeConverter
    fun fromLongToDate(timeInLong:Long):Date=
        Date(timeInLong)


}