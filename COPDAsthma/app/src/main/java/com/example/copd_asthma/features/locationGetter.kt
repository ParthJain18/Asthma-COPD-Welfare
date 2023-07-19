package com.example.copd_asthma.features

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Looper
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices








@SuppressLint("MissingPermission")
@Composable
fun getCurrentLocation(): Pair<Double, Double>? {



    val context = LocalContext.current
    val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    var currLocation by remember { mutableStateOf<Pair<Double, Double>?>(null) }
    fusedLocationClient.lastLocation
        .addOnSuccessListener { location : Location? ->
            if (location != null) {
                currLocation = Pair(location.latitude, location.longitude)
            }
        }



    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            // Permission granted, start requesting location updates
            startLocationUpdates(fusedLocationClient) { location ->
                // Update the current location when location is received
                currLocation = location
            }
        } else {
            // Permission not granted, handle accordingly
            currLocation = null
        }
    }

    LaunchedEffect(Unit) {
        val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION
        val hasLocationPermission = ContextCompat.checkSelfPermission(context, locationPermission)
        if (hasLocationPermission == PackageManager.PERMISSION_GRANTED) {
            startLocationUpdates(fusedLocationClient) { location ->
                currLocation = location
            }
        } else {
            requestPermissionLauncher.launch(locationPermission)
        }
    }










    return currLocation
}
@SuppressLint("MissingPermission")
fun startLocationUpdates(fusedLocationClient: FusedLocationProviderClient, onLocationUpdate: (Pair<Double, Double>?) -> Unit ) {

    val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                val latestLocation: Location? = locationResult.lastLocation
                latestLocation?.let { location ->
                    val latitude = location.latitude
                    val longitude = location.longitude
                    onLocationUpdate(Pair(latitude, longitude))
                }
            }
        }


        val locationRequest = LocationRequest.create().apply {
            priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
            interval = (60_000*3)
        }

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )

}
