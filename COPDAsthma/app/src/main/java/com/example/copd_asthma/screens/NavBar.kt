package com.example.copd_asthma.screens

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavBar() {
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
//                            navController.navigate(item) {
//                                navController.graph.startDestinationRoute?.let { route ->
//                                    popUpTo(route) {
//                                        saveState = true
//                                    }
//                                }
//                                launchSingleTop = true
//                                restoreState = true

                        }
                    )
                }
            }
        },

        ) {
        val pad = it

        NavHost(navController, startDestination = "home") {
            // Define your navigation graph using NavGraphBuilder
            composable("home") { HomeScreen(onLogOut = { navController.navigate("LogInScreen") }) }
            composable("settings") { SettingsScreen() }
            composable("profile") { ProfileScreen() }
            // Add more destinations as needed
        }


    }
}

@Composable
fun ProfileScreen() {
    TODO("Not yet implemented")
}

@Composable
fun SettingsScreen() {
    TODO("Not yet implemented")
}
