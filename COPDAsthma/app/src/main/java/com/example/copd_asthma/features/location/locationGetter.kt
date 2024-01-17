package com.example.copd_asthma.features.location

import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import android.location.Location
import android.util.Log
import android.widget.Toast
import com.example.copd_asthma.features.sharedPreferences.SharedPreferencesManager
import com.example.copd_asthma.fusedLocationClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.CancellationToken
import com.google.android.gms.tasks.CancellationTokenSource
import com.google.android.gms.tasks.OnTokenCanceledListener
import java.util.Locale


@SuppressLint("MissingPermission")
fun GetLocation(context: Context, onLocationReceived: (Double, Double) -> Unit) {

    fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    fusedLocationClient.getCurrentLocation(LocationRequest.PRIORITY_HIGH_ACCURACY, object : CancellationToken() {
        override fun onCanceledRequested(p0: OnTokenCanceledListener) = CancellationTokenSource().token

        override fun isCancellationRequested() = false
    })
        .addOnSuccessListener { location: Location? ->
            if (location == null)
                Toast.makeText(context, "Cannot get location.", Toast.LENGTH_SHORT).show()
            else {
                val lat = location.latitude
                val lon = location.longitude
                onLocationReceived(location.latitude, location.longitude)
            }
        }




}


suspend fun storeCityNameFromLatLngAsync(
    context: Context,
    latitude: Double,
    longitude: Double
) {
    try {
        val geocoder = Geocoder(context, Locale.getDefault())
        val addresses = geocoder.getFromLocation(latitude, longitude, 1)

        val cityName = if (addresses!!.isNotEmpty()) {
            addresses[0]?.locality ?: addresses[0]?.subAdminArea ?: addresses[0]?.adminArea ?: ""
        } else {
            "City not found"
        }

        val sharedPrefManager = SharedPreferencesManager(context)
        sharedPrefManager.storeData(city = cityName)

        Log.d("CityName", cityName)
    } catch (e: Exception) {
        Log.e("Geocoder", "Error getting city name: ${e.message}")
    }
}



