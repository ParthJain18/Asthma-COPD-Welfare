package com.example.copd_asthma.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material.ScaffoldState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.copd_asthma.NavDrawerItem
import com.parse.ParseUser
import kotlinx.coroutines.CoroutineScope

val currentUser = ParseUser.getCurrentUser()

val name = currentUser.get("name")
val sever = currentUser.get("severity")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    Scaffold(
        modifier = Modifier,
        topBar =  {
            TopAppBar(
                title = {
                    Text(text = "Jetpack Compose")
                },
                navigationIcon = {
                    IconButton(onClick = { })
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
       Column() {


        Text(text = "Hello, "+name,

            modifier = Modifier
                .padding(contentPadding)
                .fillMaxWidth(),
            fontSize = 35.sp,
            textAlign = TextAlign.Center)

        Text(text = "Your COPD Severity Level is:",
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxWidth(),
            fontSize = 25.sp,
            textAlign = TextAlign.Center)

        Text(text = ""+ sever,
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxWidth(),
            fontSize = 35.sp,
            textAlign = TextAlign.Center)
    }
       }
}
@Composable

fun Drawer(scope: CoroutineScope ,scaffoldState: ScaffoldState, navController: NavController) {
    val items = listOf(
        NavDrawerItem.Home,
        NavDrawerItem.Music,
        NavDrawerItem.Movies,
        NavDrawerItem.Books,
        NavDrawerItem.Profile,
        NavDrawerItem.Settings
    )
    Column(
        modifier = Modifier

    ) {
        // Header

        // Space between
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )
        // List of navigation items
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->

            /* Add code later */

        }
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "Developed by John Codeos",
            color = Color.White,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.CenterHorizontally)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun DrawerPreview() {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val navController = rememberNavController()
    Drawer(scope = scope, scaffoldState = scaffoldState, navController = navController)
}