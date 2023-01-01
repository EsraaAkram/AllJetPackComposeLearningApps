package com.esoapps.notecomposeapp.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.esoapps.notecomposeapp.components.NoteInputTxt
import com.esoapps.notecomposeapp.components.NoteSaveBtn
import com.esoapps.notecomposeapp.model.Note
import com.esoapps.notecomposeapp.widgets.NoteTopAppBar
import com.esoapps.notecomposeapp.widgets.NotesForm
import com.esoapps.notecomposeapp.widgets.NotesList
import com.esoapps.notecomposeapp.widgets.NotesListTitle

@ExperimentalComposeUiApi
@Composable
fun NoteScreen(
    notes: List<Note>,
    onNoteAdded: (Note) -> Unit,
    onNoteRemoved: (Note) -> Unit,
) {


    val title = remember {
        mutableStateOf("")
    }

    val description = remember {
        mutableStateOf("")
    }

    val ctx = LocalContext.current

    Column(modifier = Modifier) {

        NoteTopAppBar()

        NotesForm(
            ctx = ctx,
            title = title,
            description = description,
            onNoteAdded
        )

        NotesListTitle(notes = notes)
        NotesList(notes = notes, onNoteRemoved)


    }
}



