package com.esoapps.notecomposeapp.widgets

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.esoapps.notecomposeapp.components.NoteInputTxt
import com.esoapps.notecomposeapp.components.NoteSaveBtn
import com.esoapps.notecomposeapp.model.Note


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NotesForm(
    ctx: Context,
    title: MutableState<String>,
    description: MutableState<String>,
    onNoteAdded: (Note) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        NoteInputTxt(
            modifier = Modifier.padding(5.dp),
            text = title.value,
            label = "title",
            onTxtChange = {
                if (it.all { chars ->
                        chars.isLetter() || chars.isWhitespace()

                    }) title.value = it

            })

        NoteInputTxt(
            modifier = Modifier.padding(5.dp),
            text = description.value,
            label = "description",
            onTxtChange = {

                if (it.all { chars ->
                        chars.isLetter() || chars.isWhitespace()

                    }) description.value = it

            })

        NoteSaveBtn(
            modifier = Modifier.padding(5.dp),
            text = "Save",
            onClick = {
                if (title.value.isNotEmpty()
                    &&
                    description.value.isNotEmpty()
                ) {


                    Toast.makeText(
                        ctx,
                        "Done", Toast.LENGTH_SHORT
                    ).show()

                    onNoteAdded(
                        Note(
                            title = title.value,
                            description = description.value
                        )
                    )



                    title.value = ""
                    description.value = ""

                } else {
                    Toast.makeText(
                        ctx,
                        "Empty Note...", Toast.LENGTH_SHORT
                    ).show()
                }
            })

    }

}
