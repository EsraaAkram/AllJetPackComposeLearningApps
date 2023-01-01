package com.esoapps.notecomposeapp.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.esoapps.notecomposeapp.model.Note
import com.esoapps.notecomposeapp.util.formatDate


@Composable
fun NotesListTitle(notes: List<Note>) {
    if (notes.isNotEmpty()) {

        Divider(
            modifier = Modifier
                .padding(3.dp),
            color = MaterialTheme.colors.primary
        )

    }
}


@Composable
fun NotesList(
    notes: List<Note>,
    onNoteRemoved: (Note) -> Unit
) {

    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 5.dp)
            .background(
            color = Color.Gray.copy(alpha = .2F)
        ),
    ) {
        items(notes) { note ->

            NoteRow(
                note = note,
                onNoteClicked = {
                    onNoteRemoved(note)
                })

        }
    }

}


@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit
) {

    Surface(
        modifier = modifier
            .padding(10.dp)
            .padding(start = 7.dp, bottom = 7.dp)

            .fillMaxWidth()
            .clip(
                RoundedCornerShape(
                    topEnd = 30.dp,
                    bottomStart = 15.dp
                )
            ),
        color = MaterialTheme.colors.primary,
        elevation = 5.dp
    ) {

        Column(
            modifier = Modifier
                .padding(5.dp)
                .padding(bottom = 5.dp)
                .clickable {

                }
                .padding(5.dp),
            horizontalAlignment = Alignment.Start) {


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {


                Text(
                    modifier = Modifier,
                    text = note.title,
                    style = MaterialTheme.typography.h5,
                    color = Color.White
                )



                Icon(
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            onNoteClicked.invoke(note)
                        },
                    imageVector = Icons.Filled.Delete,
                    contentDescription = "Delete Icon",
                    tint = Color.White,
                )

            }



            Text(
                text = note.description,
                style = MaterialTheme.typography.subtitle1,
                color = Color.White
            )







            Text(
                modifier = Modifier,
                text = formatDate(note.entryDate.time),
                style = MaterialTheme.typography.caption,
                color = Color.Yellow
            )


        }


    }

}
