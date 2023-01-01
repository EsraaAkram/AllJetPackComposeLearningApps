package com.esoapps.notecomposeapp.util

import java.text.SimpleDateFormat
import java.util.*

fun formatDate(timeInLong:Long):String {

    val date=Date(timeInLong)
    val dateFormatted=SimpleDateFormat(
        "EEE, d MMM hh:mm aaa",
        Locale.getDefault()
    )

    return dateFormatted.format(timeInLong)
}
