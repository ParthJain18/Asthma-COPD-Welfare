package com.example.copd_asthma

import android.app.Application
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.LocationServices
import com.parse.Parse
import com.parse.ParseUser


var startingScreen: String = ""
lateinit var fusedLocationClient: FusedLocationProviderClient
lateinit var geofencingClient: GeofencingClient



class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        )

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        geofencingClient = LocationServices.getGeofencingClient(this)



        val currentUser : ParseUser? = ParseUser.getCurrentUser()
        startingScreen = if (currentUser == null) {
            "LogInScreen"
        } else {
            "navbar"
        }

    }
}