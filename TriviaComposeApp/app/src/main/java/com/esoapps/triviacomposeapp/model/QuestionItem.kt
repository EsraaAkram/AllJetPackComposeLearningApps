package com.esoapps.triviacomposeapp.model


data class QuestionItem(
    val question: String,
    val category:String,
    val choices:List<String>,
    val answer:String,

)