package com.example.copd_asthma.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.parse.ParseUser

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBar(onLogOut: ()-> Unit) {
    val navController = rememberNavController()
    val items = listOf("Home", "Settings", "Profile", "Log Out")
    val icons = listOf(Icons.Filled.Home, Icons.Filled.Settings, Icons.Filled.Person, Icons.Filled.ExitToApp)

    val colorStops = arrayOf(
        0.0f to Color(0xFFEBFFE6),
        0.3f to Color(0xFFEAFFE7),
        0.9f to Color.White,
        1f to Color(0xFFFFFFFF)
    )

    Scaffold(
        modifier = Modifier.background(Brush.verticalGradient(colorStops = colorStops)),
        bottomBar = {
            NavigationBar(
                containerColor = Color.White
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = { Icon(icons[index], contentDescription = item) },
                        label = { Text(item) },
                        selected = currentRoute == item,
                        onClick = {
                            when (index) {
                                0 -> navController.navigate("home")
                                1 -> navController.navigate("settings")
                                2 -> navController.navigate("profile")
                                3 -> {
                                    ParseUser.logOutInBackground {
                                        if (it == null) {
                                            onLogOut()
                                            Log.d("logout", "No errors")
                                        } else {
                                            Log.d("logout", it.toString())
                                        }
                                    }
                                }
                            }
                        }
                    )
                }
            }
        },

        ) { padding->
        NavHost(navController, startDestination = "home") {
            composable("home"
            ) { HomeScreen(padding) }
            composable("settings"
            ) { SettingScreen() }
            composable("profile"
            ) { ProfileScreen() }
        }

    }
}


