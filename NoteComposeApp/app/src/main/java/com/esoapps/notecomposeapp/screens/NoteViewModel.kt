package com.esoapps.notecomposeapp.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esoapps.notecomposeapp.model.Note
import com.esoapps.notecomposeapp.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository) : ViewModel() {

    private var _noteList = MutableStateFlow<List<Note>>(emptyList())

    var noteList = _noteList.asStateFlow()

    init {

        getAllNotes()


    }

    private fun getAllNotes() {

        viewModelScope.launch(Dispatchers.IO) {

            noteRepository.getAllNotes()
                .collect { listOfAllNotes ->

                    if (listOfAllNotes.isEmpty()) {
                        _noteList.value = emptyList()
                        Log.d("NoteViewModel", "NoteViewModel: Empty List")
                    } else {

                        _noteList.value = listOfAllNotes
                    }

                }
        }
    }


    fun addNote(note: Note) =
        viewModelScope.launch { noteRepository.addNote(note) }


    fun updateNote(note: Note) =
        viewModelScope.launch {
            noteRepository.updateNote(note)
        }

    fun deleteNote(note: Note) =
        viewModelScope.launch {
            noteRepository.deleteNote(note)
        }


}