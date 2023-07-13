package com.example.copd_asthma.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.copd_asthma.ui.theme.COPDAsthmaTheme
import com.example.copd_asthma.weatherApi.getData
import com.parse.ParseUser
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onLogOut:()-> Unit) {
    COPDAsthmaTheme {


        val currentUser = ParseUser.getCurrentUser()
        val name = currentUser.get("name").toString()
        val sever = currentUser.get("severity").toString()
        val fev1 = currentUser.get("fev1").toString()
        val gender = currentUser.get("gender").toString()
        val age = currentUser.get("age").toString()


//
//        val name = "Parth"
//        val sever = "Severe"
//        val fev1 = "78"
//        val gender = "Male"
//        val age = "18"

        val lat = 19.172
        val lon = 72.124

        var responseObj = getData(lat, lon)

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
                        onLogOut()
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

                        Card(modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                            .heightIn(min = 120.dp),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)

                        ) {
                            HeadingText(text1 = "Hey, $name!")
                            Card(modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 16.dp, start = 16.dp, end = 16.dp, bottom = 16.dp),
                                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onTertiary)

                            ) {
                                Spacer(modifier = Modifier.heightIn(20.dp))
                                RowOfText("Gender:  ", gender as String)
                                Spacer(modifier = Modifier.heightIn(7.dp))
                                RowOfText("Age:  ", age as String)
                                Spacer(modifier = Modifier.heightIn(7.dp))
                                RowOfText("COPD Severity Level:  ", sever as String)
                                Spacer(modifier = Modifier.heightIn(7.dp))
                                RowOfText("FEV 1:  ", fev1 as String)
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
                                if (responseObj != null) {
                                    RowOfText("AQI  ", responseObj.myList[0].main.aqi.toString())
                                }
                                Spacer(modifier = Modifier.heightIn(7.dp))
                                RowOfText("Age:  ", age as String)
                                Spacer(modifier = Modifier.heightIn(7.dp))
                                RowOfText("COPD Severity Level:  ", sever as String)
                                Spacer(modifier = Modifier.heightIn(7.dp))
                                RowOfText("FEV 1:  ", fev1 as String)
                                Spacer(modifier = Modifier.heightIn(20.dp))

                            }
                        }
                    }
                }
            }
    }
)
}
}


@Preview(showBackground = true)
@Composable
fun Preview1() {
    HomeScreen {}
}


//    Scaffold(
//        modifier = Modifier,
//        topBar =  {
//            TopAppBar(
//                title = {
//                    Text(text = "Jetpack Compose")
//                },
//                navigationIcon = {
//                    IconButton(onClick = { })
//                    { Icon(imageVector = Icons.Rounded.Menu, contentDescription = "Drawer Icon") }
//                },
//                actions = {
//                    IconButton(onClick = { })
//                    {
//                        Icon(
//                            imageVector = Icons.Rounded.Search,
//                            contentDescription = "",
//                            tint = Color.White
//                        )
//                    }
//                }
//            )
//        }
//    ) { contentPadding ->
//        Column() {
//
//            Text(text = "Hello! ",
//                modifier = Modifier
//                    .padding(contentPadding)
//                .fillMaxWidth(),
//            fontSize = 21.sp,
//            textAlign = TextAlign.Center)
//
//            Button(
//                onClick = {
//                    ParseUser.logOutInBackground()
//                    onLogOut()
//                }
//            ){
//                Text(text = "logout")
//            }
//
//        }
//        // Screen content
//    }
//}
//
//
//
