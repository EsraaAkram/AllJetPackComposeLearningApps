package com.esoapps.notecomposeapp.util

import androidx.room.TypeConverter
import java.util.*

class UUIDConverter {

    @TypeConverter
    fun fromUUIDToString(uuid: UUID):String=
        uuid.toString()


    @TypeConverter
    fun fromStringToUUID(uuidInString:String):UUID=
        UUID.fromString(uuidInString)
}