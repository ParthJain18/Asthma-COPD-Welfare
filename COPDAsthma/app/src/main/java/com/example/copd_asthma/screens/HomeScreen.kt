package com.example.copd_asthma.screens


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.OutlinedCard
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.parse.ParseUser
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onLogOut:()-> Unit) {
    val currentUser = ParseUser.getCurrentUser()
    val name = currentUser.get("name")
    val sever = currentUser.get("severity")
    val fev1 = currentUser.get("fev1")

//    val name = "Parth"
//    val sever = "Mild"
//    val fev1 = "78"

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
                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
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
                            Text(text = "Asthma-COPD Welfare")
                        },
                        navigationIcon = {
                            IconButton(onClick = { scope.launch { drawerState.open() }})
                            { Icon(imageVector = Icons.Rounded.Menu, contentDescription = "Drawer Icon") }
                        },
                        actions = {
                            IconButton(onClick = { })
                            {
                                Icon(
                                    imageVector = Icons.Rounded.Search,
                                    contentDescription = "",
                                    tint = Color.White
                                )
                            }
                        }
                    )
                }
            ) { contentPadding ->
                Column(modifier = Modifier
                    .padding(contentPadding)) {

                    Spacer(modifier = Modifier.heightIn(15.dp))

                    OutlinedCard(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .heightIn(min = 120.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFEDEDED)),
                        elevation = CardDefaults.cardElevation(4.dp),
                        border = BorderStroke(2.dp, Color.LightGray)

                    ) {
                        HeadingText(text1 = "Hey, $name!")
                        NormalText("FEV 1 : $fev1")
                        Row() {
                            NormalText(text1 = "Your COPD Severity Level is:")
                            NormalText(text1 = sever as String)
                        }
                        Spacer(modifier = Modifier.heightIn(20.dp))
                    }

                }
            }
        }
}
)
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
