package com.esoapps.notecomposeapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.esoapps.notecomposeapp.screens.NoteViewModel
import com.esoapps.notecomposeapp.screens.NoteScreen
import com.esoapps.notecomposeapp.ui.theme.NoteComposeAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoteComposeAppTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {


                    //var noteViewModel:NoteViewModel = viewModel()
                    val noteViewModel = viewModel<NoteViewModel>()

                    NotesApp(noteViewModel = noteViewModel)


                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NotesApp(noteViewModel: NoteViewModel) {

    val notesList = noteViewModel.noteList
        .collectAsState().value


    NoteScreen(
        notes = notesList,
        onNoteAdded = {
            noteViewModel.addNote(it)

        },

        onNoteRemoved = {
            noteViewModel.deleteNote(it)

        })


}