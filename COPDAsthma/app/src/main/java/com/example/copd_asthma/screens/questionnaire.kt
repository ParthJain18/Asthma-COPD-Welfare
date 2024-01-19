package com.example.copd_asthma.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.copd_asthma.features.utils.storeSeverity

@Composable
fun QuestionnaireScreen(modifier: Modifier = Modifier, onNavigate: () -> Unit, ) {

    val showDialog = remember { mutableStateOf(false) }
    val showScoreDialog = remember { mutableStateOf(false) }
    var catScore by remember { mutableStateOf(0) }

    var questOneSlider by remember { mutableFloatStateOf(0f) }
    var questTwoSlider by remember { mutableFloatStateOf(0f) }
    var questThreeSlider by remember { mutableFloatStateOf(0f) }
    var questFourSlider by remember { mutableFloatStateOf(0f) }
    var questFiveSlider by remember { mutableFloatStateOf(0f) }
    var questSixSlider by remember { mutableFloatStateOf(0f) }
    var questSevenSlider by remember { mutableFloatStateOf(0f) }
    var questEightSlider by remember { mutableFloatStateOf(0f) }

    val context = LocalContext.current


    if (showDialog.value) {
        HelperDialog(onDismissRequest = { showDialog.value = false })
    }

    if (showScoreDialog.value) {
        ScoreDialog(catScore) {
            showScoreDialog.value = false
            onNavigate()
        }
    }

    val colorStops = arrayOf(
        0.0f to Color(0xFFEBFFE6),
        0.3f to Color(0xFFEAFFE7),
        0.9f to Color.White,
        1f to Color(0xFFFFFFFF)
    )


    Surface(
        modifier = Modifier
            .padding(5.dp)
            .background(Brush.verticalGradient(colorStops = colorStops))
    ) {

        Column(
            modifier
                .padding(all = 10.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,

            )
        {

            Text(
                text = "Questionnaire",
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(10.dp)
            )
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, start = 10.dp, end = 10.dp)
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
            )

            {
                Text(
                    text = "I never cough:",
                    modifier
                        .padding(start = 25.dp, end = 20.dp, top = 20.dp)
                        .fillMaxWidth(),
                    fontSize = 21.sp,
                    textAlign = TextAlign.Left
                )

                LabeledSlider(
                    value = questOneSlider,
                    onValueChange = { newValue -> questOneSlider = newValue }
                )
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, start = 10.dp, end = 10.dp)
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
            )

            {


                Text(
                    text = "I have no phlegm (mucus) in my chest at all:",
                    modifier
                        .padding(start = 25.dp, end = 20.dp, top = 20.dp)
                        .fillMaxWidth(),
                    fontSize = 21.sp,
                    textAlign = TextAlign.Left
                )

                LabeledSlider(
                    value = questTwoSlider,
                    onValueChange = { newValue -> questTwoSlider = newValue }
                )
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, start = 10.dp, end = 10.dp)
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
            )

            {
                Text(
                    text = "My chest does not feel tight at all:",
                    modifier
                        .padding(start = 25.dp, end = 20.dp, top = 20.dp)
                        .fillMaxWidth(),
                    fontSize = 21.sp,
                    textAlign = TextAlign.Left
                )
                LabeledSlider(
                    value = questThreeSlider,
                    onValueChange = { newValue -> questThreeSlider = newValue }
                )
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, start = 10.dp, end = 10.dp)
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
            )

            {
                Text(
                    text = "When I walk up a hill or one flight of stairs I am not breathless:",
                    modifier
                        .padding(start = 25.dp, end = 20.dp, top = 20.dp)
                        .fillMaxWidth(),
                    fontSize = 21.sp,
                    textAlign = TextAlign.Left
                )
                LabeledSlider(
                    value = questFourSlider,
                    onValueChange = { newValue -> questFourSlider = newValue }
                )
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp, start = 20.dp, end = 20.dp)
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
            )

            {
                Text(
                    text = "I am not limited doing any activities at home:",
                    modifier
                        .padding(start = 25.dp, end = 20.dp, top = 20.dp)
                        .fillMaxWidth(),
                    fontSize = 21.sp,
                    textAlign = TextAlign.Left
                )
                LabeledSlider(
                    value = questFiveSlider,
                    onValueChange = { newValue -> questFiveSlider = newValue }
                )
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, start = 10.dp, end = 10.dp)
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
            )

            {
                Text(
                    text = "I am confident leaving my home despite my lung condition:",
                    modifier
                        .padding(start = 25.dp, end = 20.dp, top = 20.dp)
                        .fillMaxWidth(),
                    fontSize = 21.sp,
                    textAlign = TextAlign.Left
                )
                LabeledSlider(
                    value = questSixSlider,
                    onValueChange = { newValue -> questSixSlider = newValue }
                )
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, start = 10.dp, end = 10.dp)
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
            )

            {
                Text(
                    text = "I sleep soundly:",
                    modifier
                        .padding(start = 25.dp, end = 20.dp, top = 20.dp)
                        .fillMaxWidth(),
                    fontSize = 21.sp,
                    textAlign = TextAlign.Left
                )
                LabeledSlider(
                    value = questSevenSlider,
                    onValueChange = { newValue -> questSevenSlider = newValue }
                )
            }
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp, start = 10.dp, end = 10.dp)
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
            )

            {
                Text(
                    text = "I have lots of energy:",
                    modifier
                        .padding(start = 25.dp, end = 20.dp, top = 20.dp)
                        .fillMaxWidth(),
                    fontSize = 21.sp,
                    textAlign = TextAlign.Left
                )
                LabeledSlider(
                    value = questEightSlider,
                    onValueChange = { newValue -> questEightSlider = newValue }
                )
            }


            Button(
                onClick = {
                    catScore = (questOneSlider + questTwoSlider + questThreeSlider + questFourSlider + questFiveSlider + questSixSlider + questSevenSlider + questEightSlider).toInt()
                    val severity = calculateSeverity(catScore)

                    storeSeverity(severity, catScore.toString(), context)


                    showScoreDialog.value = true
                },
                modifier
                    .padding(top = 30.dp)
                    .width(200.dp)
                    .height(60.dp)
                    .align(Alignment.CenterHorizontally)
                ,
            ) {
                Text(
                    text = "Submit",
                    modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun HelperDialog(onDismissRequest: () -> Unit){
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(275.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "This questionnaire will help you and your healthcare professional measure the impact COPD (Chronic Obstructive Pulmonary Disease) is having on your wellbeing and daily life. \n This score should only be interpreted and used in cooperation with healthcare personnel.",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
                TextButton(
                    onClick = { onDismissRequest() },
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text("OK")
                }
            }
        }

    }
}

@Composable
fun ScoreDialog(CAT_score: Int, onDismissRequest: () -> Unit){
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "CAT is a questionnaire for people with COPD, which is intended to measure the effect of COPD on a person's life and how this changes over time. CAT should not be used for diagnosing COPD.",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "\n Your CAT score is: $CAT_score",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                TextButton(
                    onClick = { onDismissRequest() },
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text("OK")
                }
            }
        }

    }
}


fun calculateSeverity(score: Int): String {
    val thresholds = listOf(10, 20, 30, 41)
    val labels = listOf("Mild", "Moderate", "Severe", "Very Severe")

    for (i in thresholds.indices) {
        if (score < thresholds[i]) {
            return labels[i]
        }
    }
    return labels.last()
}

@Preview
@Composable
fun PreviewQuestionnaireScreen() {
    ScoreDialog(CAT_score = 10) {
        
    }
}