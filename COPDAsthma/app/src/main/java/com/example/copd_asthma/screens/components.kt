package com.example.copd_asthma.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun HeadingText(text1: String) {
    Text(
        text=text1,
        modifier = Modifier.padding(start = 15.dp, top = 15.dp, end= 12.dp, bottom = 10.dp),
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold
    )
}
@Composable
fun NormalText(text1: String) {
    Text(
        text=text1,
        modifier = Modifier.padding(start = 15.dp, top = 10.dp, end= 15.dp, bottom = 0.dp),
        fontSize = 18.sp
    )
}

@Preview(showBackground = true)
@Composable
fun Preview2() {
    HeadingText("Hello")
}