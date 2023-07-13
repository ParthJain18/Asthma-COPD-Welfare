package com.example.copd_asthma.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun HeadingText(text1: String) {
    Text(
        text=text1,
        modifier = Modifier.padding(20.dp),
        fontSize = 24.sp,
        fontWeight = FontWeight.ExtraBold
    )
}
@Composable
fun NormalText(text1: String) {
    Text(
        text=text1,
//        modifier = Modifier.padding(start = 15.dp, top = 10.dp, end= 15.dp, bottom = 0.dp),
        fontSize = 17.sp,
        textAlign = TextAlign.Center
    )
}

@Composable
fun RowOfText(text1: String, text2: String) {

    val color: Color = when(text2) {
        "Mild" -> Color(25, 179, 103, 255)
        "Moderate" -> Color(255, 170, 51)
        "Severe" -> Color(255, 87, 51, 228)
        "Very Severe" -> Color(210, 4, 45)

        else -> {Color.Black}
    }
    Row(modifier = Modifier.padding(start = 22.dp, end = 22.dp).fillMaxWidth()) {
        Box(
            modifier = Modifier.heightIn(30.dp).weight(6f)
        )
        {

        Text(
            text=text1,
            modifier = Modifier
                ,
            fontSize = 17.sp,
            textAlign = TextAlign.Left
        )
        }
        Box(
            modifier = Modifier.heightIn(30.dp).weight(4f),
            contentAlignment = Alignment.TopCenter
        )
        {
            Text(
                text = text2,
                modifier = Modifier,
                fontSize = 17.sp,
                fontWeight = FontWeight.SemiBold,
                color = color,
                letterSpacing = 0.7.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview2() {
    HeadingText("Hello")
}