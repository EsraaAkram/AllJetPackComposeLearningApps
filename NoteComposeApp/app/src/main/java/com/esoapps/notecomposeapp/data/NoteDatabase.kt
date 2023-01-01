package com.esoapps.notecomposeapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.esoapps.notecomposeapp.model.Note
import com.esoapps.notecomposeapp.util.DateConverter
import com.esoapps.notecomposeapp.util.UUIDConverter


@Database(entities = [Note::class], version = 1, exportSchema = false)
@TypeConverters(DateConverter::class, UUIDConverter::class)
abstract class NoteDatabase:RoomDatabase() {

    abstract fun noteDao():NoteDatabaseDao

}
