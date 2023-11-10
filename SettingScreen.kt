package com.example.copd_asthma.screens

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.copd_asthma.screens.SharedState.geofenceCount

@Composable
fun SettingScreen(padding: PaddingValues,modifier: Modifier = Modifier) {
    var userSeverity by remember { mutableStateOf("") }
    var userGeoFenceRadius by remember { mutableStateOf("") }

    Column(

        modifier
            .padding(all = 10.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,

        )
    {


        Text(
            text = "Settings",
            modifier
                .padding(start = 15.dp, end = 10.dp, top = 10.dp)
                .fillMaxWidth(),
            fontSize = 30.sp,
            textAlign = TextAlign.Center

        )
        Spacer(
            modifier.heightIn(50.dp)
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
        ) {
            Text(
                text = "Change severity level",
                modifier
                    .padding(start = 15.dp, end = 10.dp, top = 10.dp)
                    .fillMaxWidth(),
                fontSize = 25.sp,
                textAlign = TextAlign.Left
            )

            val severity = listOf("Healthy", "Moderate", "UnHealthy")
            val (severitySelect, onSeveritySelect) = remember { mutableStateOf("") }
            Column {
                severity.forEach { text ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (text == severitySelect),
                                onClick = {
                                    onSeveritySelect(text)
                                    userSeverity = text
                                }
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically


                    ) {
                        RadioButton(
                            selected = (text == severitySelect),
                            onClick = { onSeveritySelect(text) }
                        )
                        Text(
                            text = text,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }

            }

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
        ) {
            Text(
                text = "Change GeoFence size",
                modifier
                    .padding(start = 15.dp, end = 10.dp, top = 10.dp)
                    .fillMaxWidth(),
                fontSize = 25.sp,
                textAlign = TextAlign.Left
            )

            val radius = listOf("3 Kms", "5 Kms", "10 Kms")
            val (radiusSelect, onradiusSelect) = remember { mutableStateOf("") }
            Column {
                radius.forEach { text ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (text == radiusSelect),
                                onClick = {
                                    onradiusSelect(text)
                                    userGeoFenceRadius = text
                                }
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically


                    ) {
                        RadioButton(
                            selected = (text == radiusSelect),
                            onClick = { onradiusSelect(text) }
                        )
                        Text(
                            text = text,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }

            }

        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(26.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom
        ) {


            Button(
                onClick = {


                },
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth(),
                contentPadding = PaddingValues(16.dp)
            ) {
                Text("Update Settings")
            }
        }
    }
    Spacer(
        modifier.heightIn(50.dp)
    )

}


