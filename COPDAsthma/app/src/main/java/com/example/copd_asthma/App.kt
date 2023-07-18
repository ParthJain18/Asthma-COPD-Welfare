package com.example.copd_asthma

import android.app.Application
import com.parse.Parse
import com.parse.ParseUser


var startingScreen: String = ""

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

        val currentUser : ParseUser? = ParseUser.getCurrentUser()
        startingScreen = if (currentUser == null) {
            "LogInScreen"
        } else {
            "NavBar"
        }

    }
}