package com.example.copd_asthma.screens


import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.copd_asthma.ui.theme.COPDAsthmaTheme
import com.example.copd_asthma.weatherApi.getData
import com.example.copd_asthma.weatherApi.responseBody
import com.parse.ParseUser
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onLogOut: ()-> Unit) {
    COPDAsthmaTheme {


        val currentUser = ParseUser.getCurrentUser()
        val name = currentUser?.get("name")?.toString()
        val sever = currentUser?.get("severity")?.toString()
        val fev1 = currentUser?.get("fev1")?.toString()
        val gender = currentUser?.get("gender")?.toString()
        val age = currentUser?.get("age")?.toString()


//
//        val name = "Parth"
//        val sever = "Severe"
//        val fev1 = "78"
//        val gender = "Male"
//        val age = "18"

        val lat = 19.172
        val lon = 72.124

        var showCard by remember {mutableStateOf(false)}
        var responseObj by remember { mutableStateOf(responseBody) }


        LaunchedEffect(responseObj) {
            if(responseObj != null) {
                showCard = true
            }
        }
        getData(lat, lon) {
            responseObj = it
        }


        val drawerState = rememberDrawerState(DrawerValue.Closed)
        val scope = rememberCoroutineScope()

        val items = listOf(Icons.Default.Favorite, Icons.Default.Face, Icons.Default.Email)

        val selectedItem = remember { mutableStateOf(items[0]) }
        ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet (modifier = Modifier.width(300.dp)) {
                Spacer(Modifier.height(22.dp))
                items.forEach { item ->
                    NavigationDrawerItem(
                        icon = { Icon(item, contentDescription = null) },
                        label = { Text(item.name) },
                        selected = item == selectedItem.value,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem.value = item
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                        colors = NavigationDrawerItemDefaults.colors(MaterialTheme.colorScheme.surfaceVariant)
                    )

                }
                NavigationDrawerItem(
                    icon = { Icon(Icons.Default.ArrowDropDown, contentDescription = null) },
                    label = { Text("Log Out") },
                    selected = Icons.Default.ArrowDropDown == selectedItem.value,
                    onClick = {
                        scope.launch { drawerState.close() }
                        ParseUser.logOutInBackground()
                        onLogOut()
//                            if (it==null) {
//                                onLogOut()
//                                Log.d("logout", "No errors")
//
//                            }
//                            else {
//                                Log.d("logout", it.toString())
//                            }
//                        }
                    },
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                )

            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Scaffold(
                    modifier = Modifier,
                    topBar =  {
                        TopAppBar(
                            title = {
                                Text(text = "Asthma-COPD Welfare"
                                )
                            },
                            navigationIcon = {
                                IconButton(onClick = { scope.launch { drawerState.open() }})
                                { Icon(imageVector = Icons.Rounded.Menu, contentDescription = "Drawer Icon") }
                            },
                            actions = {}
                        )
                    }
                ) { contentPadding ->
                    Column(modifier = Modifier
                        .padding(contentPadding)) {

                        Spacer(modifier = Modifier.heightIn(15.dp))

                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                                .heightIn(min = 120.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)

                        ) {
                            HeadingText(text1 = "Hey, $name!")
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        top = 16.dp,
                                        start = 16.dp,
                                        end = 16.dp,
                                        bottom = 16.dp
                                    ),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onTertiary)

                            ) {
                                Spacer(modifier = Modifier.heightIn(20.dp))
                                if (gender != null) {
                                    RowOfText("Gender:  ", gender)
                                }
                                Spacer(modifier = Modifier.heightIn(7.dp))
                                if (age != null) {
                                    RowOfText("Age:  ", age)
                                }
                                Spacer(modifier = Modifier.heightIn(7.dp))
                                if (sever != null) {
                                    RowOfText("COPD Severity Level:  ", sever)
                                }
                                Spacer(modifier = Modifier.heightIn(7.dp))
                                if (fev1 != null) {
                                    RowOfText("FEV 1:  ", fev1)
                                }
                                Spacer(modifier = Modifier.heightIn(20.dp))

                            }
                        }
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                                    .heightIn(min = 120.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)

                            ) {
                                if (showCard) {
                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(
                                                top = 16.dp,
                                                start = 16.dp,
                                                end = 16.dp,
                                                bottom = 16.dp
                                            ),
                                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onTertiary)

                                    ) {

                                        Spacer(modifier = Modifier.heightIn(20.dp))
                                        RowOfText(
                                            "AQI  ",
                                            responseBody?.myList?.get(0)?.main?.aqi.toString()
                                        )

                                    }
                                }
                                else {
                                    Box(modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            top = 16.dp,
                                            start = 16.dp,
                                            end = 16.dp,
                                            bottom = 16.dp
                                        )
                                        .heightIn(100.dp)
                                        .shimmerEffect()


                                    ) {
                                    }
                                }
                            }
                        }
                    }
                }

    }
)
}
}


private fun Modifier.shimmerEffect(): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition()
    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(1500)
        )
    )
    background(
        brush = Brush.linearGradient(
            colors = listOf(
                Color(0xFFFFFFFF),
                Color(0xFFAAAAAA),
                Color(0xFFFFFFFF),
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        ),
        shape = RoundedCornerShape(10.dp)
    ).onGloballyPositioned {
        size = it.size
    }
}




@Preview(showBackground = true)
@Composable
fun Preview1() {
    HomeScreen {}
}


