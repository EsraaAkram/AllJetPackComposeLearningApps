package com.esoapps.notecomposeapp.repository

import com.esoapps.notecomposeapp.data.NoteDatabaseDao
import com.esoapps.notecomposeapp.model.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDatabaseDao: NoteDatabaseDao) {

    fun getAllNotes(): Flow<List<Note>> =noteDatabaseDao.getAllNotes()
        .flowOn(Dispatchers.IO)
        .conflate()



    suspend fun addNote(note: Note)=noteDatabaseDao.insertNote(note)

    suspend fun updateNote(note: Note)=noteDatabaseDao.updateNote(note)

    suspend fun deleteAllNote()=noteDatabaseDao.deleteAllNotes()


    suspend fun deleteNote(note: Note)=noteDatabaseDao.deleteNote(note)




}