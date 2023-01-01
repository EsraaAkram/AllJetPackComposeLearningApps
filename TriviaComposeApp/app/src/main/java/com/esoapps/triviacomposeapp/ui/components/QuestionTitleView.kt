package com.esoapps.triviacomposeapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.esoapps.triviacomposeapp.model.QuestionItem
import com.esoapps.triviacomposeapp.util.AppColors

@Composable
fun QuestionTitleView(question: QuestionItem) {
    Column {
        Text(
            modifier = Modifier
                .padding(6.dp)
                .align(alignment = Alignment.Start)
                .fillMaxHeight(0.3f),
            text = question.question,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 22.sp,
            color = AppColors.mOffWhite,
        )
    }
}