package com.example.copd_asthma.screens


import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.copd_asthma.features.sharedPreferences.SharedPreferencesManager


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(padding: PaddingValues) {

    val responseObj = SharedState.responseObj
//    val safety = "Safe"

    var isShowComp by remember { mutableStateOf(false)}

    val sharedPrefManager = SharedPreferencesManager(LocalContext.current)
    val currentUser by remember { mutableStateOf(sharedPrefManager.getUserData()) }

//    val currentUser = ParseUser.getCurrentUser()

    val name = currentUser.getString("displayName","User")
    val sever = currentUser.getString("severity","Fetching..")

//    val sever = currentUser?.get("severity")?.toString()
//    val fev1 = currentUser?.get("fev1")?.toString()
//    val fvc = currentUser?.get("fvc")?.toString()
//    val gender = currentUser?.get("gender")?.toString()
//    val age = currentUser?.get("age")?.toString()

    Log.d("name", name.toString() + " " + sever.toString())




//    val name = "Parth"
//    val sever = "Severe"
//    val fev1 = "78"
//    val gender = "Male"
//    val age = "18"
//    val aqi = "5"


//    var lat by remember { mutableStateOf<Double?>(null) }
//    var lon by remember { mutableStateOf<Double?>(null) }
//
//    var showCard by remember {mutableStateOf(false)}
//    var responseObj by remember { mutableStateOf(responseBody) }
//
//    var currentLocation by remember {mutableStateOf<Pair<Double, Double>?>(null) }
//
//    currentLocation = getCurrentLocation()
//
//    LaunchedEffect(currentLocation) {
//        if (currentLocation != null) {
//            lat = currentLocation!!.first
//            lon = currentLocation!!.second
//            Log.d("location1", "$lat $lon")
//        } else {
//            Log.d("location1", "null hai")
//        }
//
//
//        if(lat!= null && lon!= null) {
//            getData(lat!!, lon!!) {
//                responseObj = it
//            }
//        }
//
//    }












    val myList = responseObj?.myList
    val aqi = myList?.get(0)?.main?.aqi.toString()
    val safety = responseObj?.safety
    val co= myList?.get(0)?.components?.co.toString()
    val no2 = myList?.get(0)?.components?.no2.toString()
    val o3 = myList?.get(0)?.components?.o3.toString()
    val pm10 = myList?.get(0)?.components?.pm10.toString()
    val pm2_5 = myList?.get(0)?.components?.pm2_5.toString()
    val so2 = myList?.get(0)?.components?.so2.toString()
    val airQualityName = responseObj?.airQualityName.toString()

    Log.d("aqi", safety.toString())

    sharedPrefManager.storeData(
        uid = currentUser.getString("uid", "")!!,
        email = currentUser.getString("email", "")!!,
        severity = currentUser.getString("severity", "")!!
    )

    Log.d("shared", sharedPrefManager.getStoredData().getString("lat", "")!!)


    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(color = Color.Transparent)
            .padding(padding)
        ,
    ) {
        if (name != null) {
            Welcome(name)
        }
//        if (fev1 != null && fvc != null) {
//            FevCard(fev1, fvc)
//        }



        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, start = 20.dp, end = 20.dp)
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
            Box(modifier = Modifier.padding(start = 25.dp, top = 20.dp, bottom = 10.dp)) {
                HeadingText(
                    text1 = " Air Quality Index: " +
                            (((aqi.takeIf { it != "null" } ?: "") +
                                    (airQualityName.takeIf { it != "null" } ?: ""))
                                .takeIf { it.isNotBlank() }
                                ?: "Fetching.."),
                    size = 20.sp,
                    fontWeight1 = FontWeight.Medium
                )
            }
            Box(
                modifier = Modifier.padding(start = 30.dp, top = 0.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    val color: Color = when (safety) {
                        "Safe" -> Color(25, 179, 103, 255)
                        "Moderate" -> Color(255, 170, 51)
                        "Unsafe" -> Color(210, 4, 45)

                        else -> {
                            Color.Black
                        }
                    }
                    Box(
                        contentAlignment = Alignment.Center
                    ) {
                        HeadingText(
                            text1 = "This area is ",
                            size = 22.sp,
                            fontWeight1 = FontWeight.Medium
                        )
                    }
                    ColoredText(
                        text1 = safety ?: "Fetching..",
                        size = 22.sp,
                        fontWeight1 = FontWeight.ExtraBold,
                        color = color
                    )

                }
            }
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, start = 20.dp, end = 20.dp)
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
            Box(modifier = Modifier.padding(start = 30.dp, top = 20.dp, bottom = 0.dp)) {
                HeadingText(
                    text1 = "Your COPD severity level is:",
                    size = 19.sp,
                    fontWeight1 = FontWeight.Medium
                )
            }
            Spacer(modifier = Modifier.heightIn(10.dp))


            Box(modifier = Modifier.padding(start = 30.dp)) {
                val color: Color = when (sever) {
                    "Healthy" -> Color(25, 179, 103, 255)
                    "Moderate" -> Color(255, 170, 51)
                    "Unhealthy" -> Color(255, 87, 51, 228)
//                    "Very Severe" -> Color(210, 4, 45)

                    else -> {
                        Color.Black
                    }
                }
                if (sever != null) {
                    ColoredText(
                        modifier = Modifier.padding(top = 10.dp),
                        text1 = sever,
                        size = 27.sp,
                        fontWeight1 = FontWeight.Bold,
                        color = color
                    )
                }
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp, start = 20.dp, end = 20.dp)
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
            Box(modifier = Modifier
                .padding(start = 30.dp, top = 25.dp, bottom = 10.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = {
                            isShowComp = !isShowComp
                        }
                    )
                },
                contentAlignment = Alignment.Center
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    HeadingText(
                        text1 = "Show Air Components  ",
                        size = 19.sp,
                        fontWeight1 = FontWeight.Medium
                    )
                    Box(
                        contentAlignment = Alignment.Center,
                    ) {

                        Icon(
                            imageVector = if (isShowComp) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                            contentDescription = "Dropdown Icon",
                            modifier = Modifier.size(30.dp)
                        )

                    }
                }

            }
            AnimatedVisibility(
                visible = isShowComp,
                enter = fadeIn(
                    initialAlpha = 0.0f
                )
            ) {
                Column(modifier = Modifier.padding(start = 30.dp, end = 30.dp)) {
                    Spacer(modifier = Modifier.heightIn(5.dp))
                    RowOfText("CO:  ", co)
                    Spacer(modifier = Modifier.heightIn(5.dp))
                    RowOfText("SO2:  ", so2)
                    Spacer(modifier = Modifier.heightIn(5.dp))
                    RowOfText("PM2.5:  ", pm2_5)
                    Spacer(modifier = Modifier.heightIn(5.dp))
                    RowOfText("PM10:  ", pm10)
                    Spacer(modifier = Modifier.heightIn(5.dp))
                    RowOfText("NO2:  ", no2)
                    Spacer(modifier = Modifier.heightIn(5.dp))
                    RowOfText("O3:  ", o3)
                    Spacer(modifier = Modifier.heightIn(20.dp))
                }
            }
        }



    }
}







@Preview(showBackground = true)
@Composable
fun Preview1() {
    HomeScreen(padding = PaddingValues(0.dp))
}


