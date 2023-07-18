package com.example.copd_asthma.data.navigationBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavigationScreens(val route: String, val icon: ImageVector) {
    object Home : BottomNavigationScreens("HomeScreen", Icons.Filled.Home)
    object Settings : BottomNavigationScreens("Settings", Icons.Filled.Settings)
    object Profile : BottomNavigationScreens("Profile", Icons.Filled.Face)
}