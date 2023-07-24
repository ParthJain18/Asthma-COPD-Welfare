package com.example.copd_asthma.features.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.core.content.ContextCompat

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun getLocation(context: Context, onLocationReceived: (Double, Double) -> Unit) {
    val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {
            // Once the location is received, remove the listener to stop receiving updates
            locationManager.removeUpdates(this)
            onLocationReceived(location.latitude, location.longitude)
        }
    }
    
    LaunchedEffect(Unit) {
        val locationPermission = listOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_BACKGROUND_LOCATION)

        locationPermission.forEach {
            val hasLocationPermission = ContextCompat.checkSelfPermission(context, it)
            if (hasLocationPermission != PackageManager.PERMISSION_GRANTED) {
                //TODO: Ask for permissions
            }
        }

    }

    locationManager.requestSingleUpdate(LocationManager.GPS_PROVIDER, locationListener, null)





//    locationManager.requestSingleUpdate(LocationManager.NETWORK_PROVIDER, locationListener, null)
}



//@SuppressLint("MissingPermission")
//@Composable
//fun getCurrentLocation(): Pair<Double, Double>? {
//
//    val context = LocalContext.current
//    val fusedLocationClient: FusedLocationProviderClient =
//        LocationServices.getFusedLocationProviderClient(context)
//
//    var currLocation by remember { mutableStateOf<Pair<Double, Double>?>(null) }
//    fusedLocationClient.lastLocation
//        .addOnSuccessListener { location : Location? ->
//            if (location != null) {
//                currLocation = Pair(location.latitude, location.longitude)
//            }
//        }
//
//
//
//    val requestPermissionLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.RequestPermission()
//    ) { isGranted ->
//        if (isGranted) {
//            startLocationUpdates(fusedLocationClient) { location ->
//                currLocation = location
//            }
//        } else {
//            currLocation = null
//        }
//    }
//
//    LaunchedEffect(Unit) {
//        val locationPermission = Manifest.permission.ACCESS_FINE_LOCATION
//        val hasLocationPermission = ContextCompat.checkSelfPermission(context, locationPermission)
//        if (hasLocationPermission == PackageManager.PERMISSION_GRANTED) {
//            startLocationUpdates(fusedLocationClient) { location ->
//                currLocation = location
//            }
//        } else {
//            requestPermissionLauncher.launch(locationPermission)
//        }
//    }
//
//    return currLocation
//}
//@SuppressLint("MissingPermission")
//fun startLocationUpdates(fusedLocationClient: FusedLocationProviderClient, onLocationUpdate: (Pair<Double, Double>?) -> Unit ) {
//
//    val locationCallback = object : LocationCallback() {
//            override fun onLocationResult(locationResult: LocationResult) {
//                val latestLocation: Location? = locationResult.lastLocation
//                latestLocation?.let { location ->
//                    val latitude = location.latitude
//                    val longitude = location.longitude
//                    onLocationUpdate(Pair(latitude, longitude))
//                }
//            }
//        }
//
//
//        val locationRequest = LocationRequest.create().apply {
//            priority = LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY
//            interval = (60_000*3)
//        }
//
//        fusedLocationClient.requestLocationUpdates(
//            locationRequest,
//            locationCallback,
//            Looper.getMainLooper()
//        )
//
//}
