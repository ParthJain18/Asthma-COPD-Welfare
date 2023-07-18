package com.example.copd_asthma.features

import android.annotation.SuppressLint
import android.location.Location
import android.os.Looper
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices


@SuppressLint("MissingPermission")
@Composable
fun getCurrentLocation(): Pair<Double, Double>? {
    var currLocation by remember { mutableStateOf<Pair<Double, Double>?>(null) }
    val context = LocalContext.current
    val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    val locationCallback = object : LocationCallback() {
        @SuppressLint("MissingPermission")
        override fun onLocationResult(locationResult: LocationResult) {
            val latestLocation: Location? = locationResult.lastLocation
            latestLocation?.let { location ->
                val latitude = location.latitude
                val longitude = location.longitude
                currLocation = Pair(latitude, longitude)
            }
        }
    }

    val locationRequest = LocationRequest.create().apply {
        priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        interval = 5000
    }

    fusedLocationClient.requestLocationUpdates(
        locationRequest,
        locationCallback,
        Looper.getMainLooper()
    )

    return currLocation
}