package com.example.copd_asthma.screens


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.parse.ParseUser


//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun HomeScreen1(onLogOut: ()-> Unit) {
//    COPDAsthmaTheme {
//
//
////        val currentUser = ParseUser.getCurrentUser()
////        val name = currentUser?.get("name")?.toString()
////        val sever = currentUser?.get("severity")?.toString()
////        val fev1 = currentUser?.get("fev1")?.toString()
////        val gender = currentUser?.get("gender")?.toString()
////        val age = currentUser?.get("age")?.toString()
//
//
////
//        val name = "Parth"
//        val sever = "Severe"
//        val fev1 = "78"
//        val gender = "Male"
//        val age = "18"
//
//        val lat = 19.172
//        val lon = 72.124
//
//
//
//        var showCard by remember {mutableStateOf(false)}
//        var responseObj by remember { mutableStateOf(responseBody) }
//
//
//        LaunchedEffect(responseObj) {
//            if(responseObj != null) {
//                showCard = true
//            }
//        }
//        getData(lat, lon) {
//            responseObj = it
//        }
//
//        val aqi = responseObj?.myList?.get(0)?.main?.aqi.toString()
//        val co= responseObj?.myList?.get(0)?.components?.co.toString()
//        val no2 = responseObj?.myList?.get(0)?.components?.no2.toString()
//        val o3 = responseObj?.myList?.get(0)?.components?.o3.toString()
//        val pm10 = responseObj?.myList?.get(0)?.components?.pm10.toString()
//        val pm2_5 = responseObj?.myList?.get(0)?.components?.pm2_5.toString()
//        val so2 = responseObj?.myList?.get(0)?.components?.so2.toString()
//
//
//
//        val drawerState = rememberDrawerState(DrawerValue.Closed)
//        val scope = rememberCoroutineScope()
//
//        val items = listOf(Icons.Default.Favorite, Icons.Default.Face, Icons.Default.Email)
//
//        val selectedItem = remember { mutableStateOf(items[0]) }
//        ModalNavigationDrawer(
//        drawerState = drawerState,
//        drawerContent = {
//            ModalDrawerSheet (modifier = Modifier.width(300.dp)) {
//                Spacer(Modifier.height(22.dp))
//                items.forEach { item ->
//                    NavigationDrawerItem(
//                        icon = { Icon(item, contentDescription = null) },
//                        label = { Text(item.name) },
//                        selected = item == selectedItem.value,
//                        onClick = {
//                            scope.launch { drawerState.close() }
//                            selectedItem.value = item
//                        },
//                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
//                        colors = NavigationDrawerItemDefaults.colors(MaterialTheme.colorScheme.surfaceVariant)
//                    )
//
//                }
//                NavigationDrawerItem(
//                    icon = { Icon(Icons.Default.ArrowDropDown, contentDescription = null) },
//                    label = { Text("Log Out") },
//                    selected = Icons.Default.ArrowDropDown == selectedItem.value,
//                    onClick = {
//                        scope.launch { drawerState.close() }
//                        ParseUser.logOutInBackground()
//                        onLogOut()
////                            if (it==null) {
////                                onLogOut()
////                                Log.d("logout", "No errors")
////
////                            }
////                            else {
////                                Log.d("logout", it.toString())
////                            }
////                        }
//                    },
//                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
//                )
//
//            }
//        },
//        content = {
//            Column(
//                modifier = Modifier
//                    .fillMaxSize(),
//                horizontalAlignment = Alignment.CenterHorizontally
//            ) {
//                Scaffold(
//                    modifier = Modifier,
//                    topBar =  {
//                        TopAppBar(
//                            title = {
//                                Text(text = "Asthma-COPD Welfare"
//                                )
//                            },
//                            navigationIcon = {
//                                IconButton(onClick = { scope.launch { drawerState.open() }})
//                                { Icon(imageVector = Icons.Rounded.Menu, contentDescription = "Drawer Icon") }
//                            },
//                            actions = {}
//                        )
//                    }
//                ) { contentPadding ->
//                    Column(modifier = Modifier
//                        .padding(contentPadding)) {
//
//                        Spacer(modifier = Modifier.heightIn(15.dp))
//
//                        Card(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
//                                .heightIn(min = 120.dp),
//                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
//
//                        ) {
//                            HeadingText(text1 = "Hey, $name!")
//                            Card(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(
//                                        top = 16.dp,
//                                        start = 16.dp,
//                                        end = 16.dp,
//                                        bottom = 16.dp
//                                    ),
//                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onTertiary)
//
//                            ) {
//                                Spacer(modifier = Modifier.heightIn(20.dp))
//                                if (gender != null) {
//                                    RowOfText("Gender:  ", gender)
//                                }
//                                Spacer(modifier = Modifier.heightIn(7.dp))
//                                if (age != null) {
//                                    RowOfText("Age:  ", age)
//                                }
//                                Spacer(modifier = Modifier.heightIn(7.dp))
//                                if (sever != null) {
//                                    RowOfText("COPD Severity Level:  ", sever)
//                                }
//                                Spacer(modifier = Modifier.heightIn(7.dp))
//                                if (fev1 != null) {
//                                    RowOfText("FEV 1:  ", fev1)
//                                }
//                                Spacer(modifier = Modifier.heightIn(20.dp))
//
//                            }
//                        }
//                            Card(
//                                modifier = Modifier
//                                    .fillMaxWidth()
//                                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
//                                    .heightIn(min = 120.dp),
//                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
//
//                            ) {
//                                if (showCard) {
//                                    Card(
//                                        modifier = Modifier
//                                            .fillMaxWidth()
//                                            .padding(
//                                                top = 16.dp,
//                                                start = 16.dp,
//                                                end = 16.dp,
//                                                bottom = 16.dp
//                                            ),
//                                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onTertiary)
//
//                                    ) {
//                                        HeadingText("Your current AQI is: $aqi")
//                                        Card(
//
//                                            modifier = Modifier
//                                                .fillMaxWidth()
//                                                .padding(
//                                                    top = 16.dp,
//                                                    start = 16.dp,
//                                                    end = 16.dp,
//                                                    bottom = 16.dp
//                                                ),
//                                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onTertiary)
//
//                                        ) {
//                                            HeadingText(text1 = "Major Air Pollutants are:")
//                                            Spacer(modifier = Modifier.heightIn(7.dp))
//                                            RowOfText("CO:  ", co )
//                                            Spacer(modifier = Modifier.heightIn(7.dp))
//                                            RowOfText("SO2:  ",so2)
//                                            Spacer(modifier = Modifier.heightIn(7.dp))
//                                            RowOfText("PM2.5:  ",pm2_5.toString() )
//                                            Spacer(modifier = Modifier.heightIn(7.dp))
//                                            RowOfText("PM10:  ", pm10.toString() )
//                                            Spacer(modifier = Modifier.heightIn(7.dp))
//                                            RowOfText("NO2:  ", no2.toString() )
//                                            Spacer(modifier = Modifier.heightIn(7.dp))
//                                            RowOfText("O3:  ", o3.toString() )
//                                            Spacer(modifier = Modifier.heightIn(20.dp))
//
//                                        }
//                                    }
//
//                                }
//                                else {
//                                    Box(modifier = Modifier
//                                        .fillMaxWidth()
//                                        .padding(
//                                            top = 16.dp,
//                                            start = 16.dp,
//                                            end = 16.dp,
//                                            bottom = 16.dp
//                                        )
//                                        .heightIn(120.dp)
//                                        .shimmerEffect()
//
//
//                                    ) {
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//
//    }
//)
//}
//}


//private fun Modifier.shimmerEffect(): Modifier = composed {
//    var size by remember {
//        mutableStateOf(IntSize.Zero)
//    }
//    val transition = rememberInfiniteTransition()
//    val startOffsetX by transition.animateFloat(
//        initialValue = -2 * size.width.toFloat(),
//        targetValue = 2 * size.width.toFloat(),
//        animationSpec = infiniteRepeatable(
//            animation = tween(1500)
//        )
//    )
//    background(
//        brush = Brush.linearGradient(
//            colors = listOf(
//                Color(0xFFFFFFFF),
//                Color(0xFFAAAAAA),
//                Color(0xFFFFFFFF),
//            ),
//            start = Offset(startOffsetX, 0f),
//            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
//        ),
//        shape = RoundedCornerShape(10.dp)
//    ).onGloballyPositioned {
//        size = it.size
//    }
//}







@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(padding: PaddingValues) {

    val responseObj = SharedState.responseObj
    val safety = "Safe"

    var isShowComp by remember { mutableStateOf(false)}


    val currentUser = ParseUser.getCurrentUser()
    val name = currentUser?.get("name")?.toString()
    val sever = currentUser?.get("severity")?.toString()
    val fev1 = currentUser?.get("fev1")?.toString()
    val fvc = currentUser?.get("fvc")?.toString()
    val gender = currentUser?.get("gender")?.toString()
    val age = currentUser?.get("age")?.toString()




//    val name = "Parth"
//    val sever = "Severe"
//    val fev1 = "78"
//    val gender = "Male"
//    val age = "18"


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













    val aqi = responseObj?.myList?.get(0)?.main?.aqi.toString()
    val co= responseObj?.myList?.get(0)?.components?.co.toString()
    val no2 = responseObj?.myList?.get(0)?.components?.no2.toString()
    val o3 = responseObj?.myList?.get(0)?.components?.o3.toString()
    val pm10 = responseObj?.myList?.get(0)?.components?.pm10.toString()
    val pm2_5 = responseObj?.myList?.get(0)?.components?.pm2_5.toString()
    val so2 = responseObj?.myList?.get(0)?.components?.so2.toString()

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
        if (fev1 != null && fvc != null) {
            FevCard(fev1, fvc)
        }
        Box(modifier = Modifier.padding(start = 30.dp, top = 25.dp, bottom = 10.dp)) {
            HeadingText(
                text1 = "Your COPD severity level is:",
                size = 19.sp,
                fontWeight1 = FontWeight.Medium
            )
        }
        Box(modifier = Modifier.padding(start = 30.dp)) {
            val color: Color = when(sever) {
                "Mild" -> Color(25, 179, 103, 255)
                "Moderate" -> Color(255, 170, 51)
                "Severe" -> Color(255, 87, 51, 228)
                "Very Severe" -> Color(210, 4, 45)

                else -> {Color.Black}
            }
            if (sever != null) {
                ColoredText(
                    text1 = sever,
                    size = 30.sp,
                    fontWeight1 = FontWeight.Bold,
                    color = color
                )
            }
        }
        Box(modifier = Modifier.padding(start = 30.dp, top = 25.dp, bottom = 10.dp)) {
            HeadingText(
                text1 = "Current Air Quality Index is: $aqi",
                size = 19.sp,
                fontWeight1 = FontWeight.Medium
            )
        }
        Box(modifier = Modifier.padding(start = 30.dp, top = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                val color: Color = when(safety) {
                    "Safe" -> Color(25, 179, 103, 255)
                    "Mildly Hazardous" -> Color(255, 170, 51)
                    "Hazardous" -> Color(255, 87, 51, 228)
//                            "Very Severe" -> Color(210, 4, 45)

                    else -> {Color.Black}
                }
                Box(contentAlignment = Alignment.Center
                ) {
                    HeadingText(
                        text1 = "This area is ",
                        size = 19.sp,
                        fontWeight1 = FontWeight.Medium
                    )
                }
                ColoredText(
                    text1 = safety,
                    size = 25.sp,
                    fontWeight1 = FontWeight.ExtraBold,
                    color = color
                )
            }
        }
        Box(modifier = Modifier
            .padding(start = 30.dp, top = 25.dp, bottom = 10.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        isShowComp = !isShowComp
                    }
                )
            }
            ,
            contentAlignment = Alignment.Center
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                HeadingText(
                    text1 = "Show Air Components  ",
                    size = 19.sp,
                    fontWeight1 = FontWeight.Medium
                )
                Box(contentAlignment = Alignment.Center,
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





@Preview(showBackground = true)
@Composable
fun Preview1() {
}


