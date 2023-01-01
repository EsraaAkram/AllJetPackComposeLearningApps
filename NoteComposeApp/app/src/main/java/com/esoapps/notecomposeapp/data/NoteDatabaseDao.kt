package com.esoapps.notecomposeapp.data

import androidx.room.*
import com.esoapps.notecomposeapp.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDatabaseDao {

    @Query("SELECT * from notes_tbl")
    fun getAllNotes():Flow<List<Note>>


    @Query("SELECT * from notes_tbl where id=:id")
    suspend fun getNoteById(id:String): Note


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: Note)


    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNote(note: Note)

    @Query("DELETE from notes_tbl")
    suspend fun deleteAllNotes()


    @Delete
    suspend fun deleteNote(note: Note)




}