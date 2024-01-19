package com.example.copd_asthma.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.copd_asthma.R
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment


@Composable
fun HeadingText(text1: String, size: TextUnit, fontWeight1: FontWeight) {
    Text(
        text=text1,
//        modifier= Modifier.padding(bottom = 18.dp),
        fontSize = size,
        fontWeight = fontWeight1

    )
}
@Composable
fun ColoredText(text1: String, size: TextUnit, fontWeight1: FontWeight, color: Color, modifier: Modifier = Modifier) {
    Text(
        text=text1,
        fontSize = size,
        fontWeight = fontWeight1,
        color = color
    )
}

@Composable
fun Welcome(name: String) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 26.dp, bottom = 15.dp, start = 20.dp, end = 20.dp)
            .heightIn(min = 100.dp)
            .clip(RoundedCornerShape(8.dp))
            .shadow(
                elevation = 3.dp,
                spotColor = Color(0x26000000),
                ambientColor = Color(0x26000000)
            )
            .shadow(
                elevation = 2.dp,
                spotColor = Color(0x4D000000),
                ambientColor = Color(0x4D000000)
            )
            .border(
                width = 1.dp,
                color = Color(0xFFDCDCDC),
                shape = RoundedCornerShape(size = 16.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(top = 40.dp, start = 30.dp, end = 30.dp)) {
            Row {
                Column(modifier = Modifier.weight(7f)) {
                    HeadingText(text1 = "Welcome!", size = 38.sp, fontWeight1 = FontWeight.Light)
                    Spacer(modifier = Modifier.heightIn(10.dp))
                    Box(modifier = Modifier.heightIn(max = 40.dp)) {
                        HeadingText(text1 = name, size = 33.sp, fontWeight1 = FontWeight.ExtraBold)
                    }
                }
                Box(
                    modifier = Modifier
                        .weight(3f)
                        .fillMaxSize()
                        .align(Alignment.CenterVertically)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.avatar_m_1),
                        contentDescription = "avatar",
                        modifier = Modifier
                            .heightIn(80.dp)
                            .fillMaxSize()
                    )
                }
            }
            Spacer(modifier = Modifier.heightIn(18.dp))
            ColoredText(
                text1 = "PureAir: Stay Informed, Stay Healthy",
                size = 13.sp,
                fontWeight1 = FontWeight.Medium,
                Color(0xFF6B6B6B)
            )
            Spacer(modifier = Modifier.heightIn(18.dp))


        }
    }
}

@Composable
fun FevCard(fev1: String, fvc: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 26.dp, start = 20.dp, end = 20.dp)
            .heightIn(min = 100.dp)
            .clip(RoundedCornerShape(8.dp))
            .shadow(
                elevation = 3.dp,
                spotColor = Color(0x26000000),
                ambientColor = Color(0x26000000)
            )
            .shadow(
                elevation = 2.dp,
                spotColor = Color(0x4D000000),
                ambientColor = Color(0x4D000000)
            )
            .border(
                width = 1.dp,
                color = Color(0xFFDCDCDC),
                shape = RoundedCornerShape(size = 16.dp)
            ),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CardContent(id = R.drawable.fev12, label = "FEV 1", value = fev1)
                Divider(
                    color = Color(0xFFABABAB),
                    modifier = Modifier
                        .height(90.dp)
                        .width(1.dp)
                )
                CardContent(id = R.drawable.fvc1, label = "FVC", value = fvc)
            }

        }
    }
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
    Row(modifier = Modifier
        .padding(start = 22.dp, end = 22.dp)
        .fillMaxWidth()) {
        Box(
            modifier = Modifier
                .heightIn(30.dp)
                .weight(6f)
        )
        {

        Text(
            text=text1,
            modifier = Modifier,
            fontSize = 17.sp,
            textAlign = TextAlign.Left
        )
        }
        Box(
            modifier = Modifier
                .heightIn(30.dp)
                .weight(4f),
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

@Composable
fun CardContent(id: Int, label: String, value: String) {
    Row(modifier = Modifier.width(160.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .weight(0.4f)
                .padding(start = 5.dp, end = 5.dp)
            ,
            contentAlignment = Alignment.Center,

        ) {
        Image(
            painter = painterResource(id = id),
            contentDescription ="",
            modifier = Modifier.size(120.dp)
        )
        }
        Box(
            Modifier
                .fillMaxSize()
                .weight(0.6f), contentAlignment = Alignment.Center) {
            Column(
                modifier = Modifier
                    .padding(start = 15.dp, end = 10.dp, top = 20.dp, bottom = 10.dp)
                    .fillMaxSize()
                ,
                verticalArrangement = Arrangement.Center
            ) {
                ColoredText(
                    text1 = label,
                    size = 16.sp,
                    fontWeight1 = FontWeight.Medium,
                    color = Color(0xFF6B6B6B)
                )
                HeadingText(text1 = "$value L", size = 29.sp, fontWeight1 = FontWeight.ExtraBold)
            }
        }
    }

}


@Composable
fun AlertBox() {
    Dialog(
        onDismissRequest = { },
    ) {
        Card(
            modifier = Modifier
                .height(100.dp)
                .width(600.dp)
            ,
            colors = CardDefaults.cardColors(Color.White),
            shape = RoundedCornerShape(20)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                Box(modifier = Modifier
                    .weight(3f),
                    contentAlignment = Alignment.Center
                    ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(40.dp)
                    )
                }
                Text(
                    text= "Logging Out...",
                    modifier = Modifier
                        .weight(7f)
                        .padding(start = 15.dp),
                    fontSize = 16.sp,
                )
            }
        }
    }
}


@Composable
fun LabeledSlider(
    value: Float,
    onValueChange: (Float) -> Unit
) {
    val labels = listOf("Very\nTrue", "True", "Maybe\nTrue", "Maybe\nFalse", "False", "Very\nFalse")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            for (i in 0..5) {
                Text(
                    text = labels[i],
                    fontSize = 12.sp,
                    modifier = Modifier.weight(1f)
                        .align(Alignment.CenterVertically),
                    textAlign = TextAlign.Center
                )
            }
        }

        Slider(
            value = value,
            onValueChange = { newValue ->
                onValueChange(newValue)
            },
            valueRange = 0f..5f,
            steps = 4,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 2.dp, horizontal = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun Preview2() {
    LabeledSlider(
        value = 2.0f,
        onValueChange = { }
    )
}