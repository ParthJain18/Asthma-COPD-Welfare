package com.example.copd_asthma.screens

import android.Manifest
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.copd_asthma.data.airQuality.airQuality
import com.example.copd_asthma.features.location.GeofenceHelper
import com.example.copd_asthma.features.location.GetLocation
import com.example.copd_asthma.features.weatherApi.getData
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.parse.ParseUser



@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
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

    val fineLocationPermState = rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)
    var backgroundLocationPermState = rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        backgroundLocationPermState =
            rememberPermissionState(permission = Manifest.permission.ACCESS_BACKGROUND_LOCATION)
    }
    var showPermissionDialog by remember { mutableStateOf(false) }
    var askPerm by remember { mutableStateOf(false) }


    @Composable
    fun showCustomPermissionDialog() {

        AlertDialog(
            onDismissRequest = { showPermissionDialog = false },
            confirmButton = {
                Text(
                    text = "Grant Permission",
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            showPermissionDialog = false
                            askPerm = true
                        }
                        .padding(10.dp),
                    textAlign = TextAlign.Center,
                )
            },
            title = { Text(text = "Permission Needed") },
            text = {
                Text(
                    text = "This app needs access to your background location to provide relevant services. If you don't see a dialog after this, kindly set location permissions as 'Allow all the time' manually in the app settings."
                )
            },
            containerColor = Color.White
        )
    }


    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
        if(!fineLocationPermState.status.isGranted){
            showPermissionDialog = true
            showCustomPermissionDialog()
        }
    }
    else {
        if(!fineLocationPermState.status.isGranted || !backgroundLocationPermState.status.isGranted){
            showPermissionDialog = true
            showCustomPermissionDialog()
        }
    }

    LaunchedEffect(askPerm) {
        if (askPerm) {
            if (!fineLocationPermState.status.isGranted) {
                fineLocationPermState.launchPermissionRequest()
                askPerm = false
            }
        }
        else {

            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
                try {
                    GetLocation(context) { latitude, longitude ->
                        Log.d("location12", "$latitude $longitude")
                        createGeofenceAt(latitude, longitude, context) }
                }
                catch (e:  java.lang.SecurityException) {
                    Log.d("permissions", e.toString())
                    askPerm = false
                }
            }

        }
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        LaunchedEffect(backgroundLocationPermState.status) {
            if (backgroundLocationPermState.status.isGranted) {
                try {
                    GetLocation(context) { latitude, longitude ->
                        Log.d("location12", "$latitude $longitude")
                        createGeofenceAt(latitude, longitude, context)
                    }
                } catch (e: java.lang.SecurityException) {
                    Log.d("permissions", e.toString())
                    askPerm = false
                }
            }
        }
    }


    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        LaunchedEffect(fineLocationPermState.status) {
            if (fineLocationPermState.status.isGranted) {
                if (!backgroundLocationPermState.status.isGranted) {
                    Log.d("permissions", "background")
                    backgroundLocationPermState.launchPermissionRequest()
                }
            }

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
            ) { HomeScreen(padding) }
            composable("settings"
            ) { SettingScreen(padding) }
            composable("profile"
            ) { ProfileScreen(padding) }
        }

    }

}

object SharedState {
    var responseObj by mutableStateOf<airQuality?>(null)
}

fun createGeofenceAt(lat: Double?, lon: Double?, context: Context) {
    if (lat != null && lon != null) {
        Log.d("location12", "$lat $lon")
        getData(lat, lon) { SharedState.responseObj = it }
        val geofenceHelper = GeofenceHelper(context)
        geofenceHelper.addGeofence("GEOFENCE_1", lat, lon, 500f)
    }
}

