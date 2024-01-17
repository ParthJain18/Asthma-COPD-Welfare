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
import androidx.compose.runtime.SideEffect
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
import com.example.copd_asthma.features.sharedPreferences.SharedPreferencesManager
import com.example.copd_asthma.features.location.GeofenceHelper
import com.example.copd_asthma.features.location.GetLocation
import com.example.copd_asthma.features.location.storeCityNameFromLatLngAsync
import com.example.copd_asthma.features.weatherApi.getData
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@Composable
fun NavBar(onLogOut: ()-> Unit) {



    val navController = rememberNavController()
    val items = listOf("Home", "Settings", "Log Out")
    val icons = listOf(Icons.Filled.Home, Icons.Filled.Settings, Icons.Filled.ExitToApp)
    val auth = FirebaseAuth.getInstance()

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

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val notificationPermState = rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)

        if (!notificationPermState.status.isGranted) {
            SideEffect {
                notificationPermState.launchPermissionRequest()
            }
        }
    }

    var showPermissionDialog by remember { mutableStateOf(false) }
    var askPerm by remember { mutableStateOf(false) }
    var isLoggingOut by remember { mutableStateOf(false)}


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

    if (showPermissionDialog) {
        showCustomPermissionDialog()
    }


    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
        if(!fineLocationPermState.status.isGranted){
            showPermissionDialog = true
        }
    }
    else {
        if(!fineLocationPermState.status.isGranted || !backgroundLocationPermState.status.isGranted){
            showPermissionDialog = true
//            showCustomPermissionDialog()
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
                showPermissionDialog = false
                try {
                    GetLocation(context) { latitude, longitude ->
                        Log.d("location12", "$latitude $longitude")
                        createGeofenceAt(latitude, longitude, context)

                    }
                }
                catch (e:  SecurityException) {
                    Log.d("permissions", e.toString())
                    askPerm = false
                }
            }

        }
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        LaunchedEffect(backgroundLocationPermState.status) {
            if (backgroundLocationPermState.status.isGranted) {
                showPermissionDialog = false
                try {
                    GetLocation(context) { latitude, longitude ->
                        Log.d("location12", "$latitude $longitude")
                        createGeofenceAt(latitude, longitude, context)
                    }
                } catch (e: SecurityException) {
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





    if (isLoggingOut) {
        AlertBox()
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
//                                2 -> navController.navigate("profile")
                                2 -> {
                                    isLoggingOut = true
                                    val geofenceHelper = GeofenceHelper(context)
                                    geofenceHelper.removeGeofence(listOf("GEOFENCE_1"))
                                    auth.signOut()
                                    onLogOut()
                                    val sharedPrefManager = SharedPreferencesManager(context)
                                    sharedPrefManager.clearPreferences()


//                                    ParseUser.logOutInBackground {
//                                        if (it == null) {
//                                            onLogOut()
//                                            val geofenceHelper = GeofenceHelper(context)
//                                            geofenceHelper.removeGeofence(listOf("GEOFENCE_1"))
//
//                                            Timer().schedule(500) {
//                                                Log.d("logout", "No errors")
//                                                isLoggingOut = false
//                                            }
//                                        } else {
//                                            Log.d("logout", it.toString())
//                                            isLoggingOut = false
//                                        }
//                                    }

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
//            composable("profile"
//            ) { ProfileScreen(padding) }
        }

    }


}

object SharedState {
    var responseObj by mutableStateOf<airQuality?>(null)
}

fun createGeofenceAt(lat: Double?, lon: Double?, context: Context) {
    if (lat != null && lon != null) {
        Log.d("location12", "$lat $lon")

        val sharedPrefManager = SharedPreferencesManager(context)

        Log.d("shared_1", sharedPrefManager.getStoredData().all.toString())



        getData(lat, lon) { it ->

            SharedState.responseObj = it
            val safety = when (SharedState.responseObj?.myList?.get(0)?.main?.aqi) {
                1 -> "Safe"
                2 -> "Safe"
                3 -> "Moderate"
                4 -> "Moderate"
                5 -> "Unsafe"
                else -> "Unknown"
            }
            val airQualityName = when (SharedState.responseObj?.myList?.get(0)?.main?.aqi) {
                5 -> "Very Poor"
                4 -> "Poor"
                3 -> "Moderate"
                2 -> "Fair"
                1 -> "Good"
                else -> "Unknown"
            }
            if (SharedState.responseObj != null) {
                SharedState.responseObj!!.safety = safety
                SharedState.responseObj!!.airQualityName = " ($airQualityName)"
            }

            CoroutineScope(Dispatchers.IO).launch {
                storeCityNameFromLatLngAsync(context, lat, lon)
            }

            sharedPrefManager.storeData(
                aqi = SharedState.responseObj?.myList?.get(0)?.main?.aqi.toString(),
                coords = listOf(lat, lon),
                safety = safety
                )
            Log.d("shared_2", sharedPrefManager.getStoredData().all.toString())

        }
        val radius = (sharedPrefManager
            .getSettings()
            .getString("radius", "3 Kms") ?. split(" ")?.get(0) + "000").toFloat()

        Log.d("radius", radius.toString())

        val geofenceHelper = GeofenceHelper(context)
        geofenceHelper.addGeofence("GEOFENCE_1", lat, lon, radius)
    }
}

