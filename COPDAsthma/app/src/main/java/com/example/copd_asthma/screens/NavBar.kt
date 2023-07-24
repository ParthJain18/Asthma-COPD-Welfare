package com.example.copd_asthma.screens

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.copd_asthma.features.location.GeofenceHelper
import com.example.copd_asthma.features.location.getLocation
import com.example.copd_asthma.features.weatherApi.getData
import com.example.copd_asthma.features.weatherApi.responseBody
import com.parse.ParseUser

@RequiresApi(Build.VERSION_CODES.Q)
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



    val context = LocalContext.current
    var lat by remember { mutableStateOf<Double?>(null) }
    var lon by remember { mutableStateOf<Double?>(null) }

    var responseObj by remember { mutableStateOf(responseBody) }




    getLocation(context) { latitude, longitude ->
        lat = latitude
        lon = longitude
    }

    if(lat!= null && lon!= null) {

        Log.d("location1", "$lat $lon")
        getData(lat!!, lon!!) {
            responseObj = it
            val geofenceHelper = GeofenceHelper(context)
            geofenceHelper.addGeofence("GEOFENCE_1", lat!!, lon!!, 50f)
        }
    }

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
            ) { HomeScreen(padding, responseObj) }
            composable("settings"
            ) { SettingScreen(padding) }
            composable("profile"
            ) { ProfileScreen(padding) }
        }

    }
}


