package com.example.copd_asthma

import android.app.Application
import android.content.Context
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.GeofencingClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.example.copd_asthma.features.periodic_function.schedulePeriodicWork


var startingScreen: String = ""
lateinit var fusedLocationClient: FusedLocationProviderClient
lateinit var geofencingClient: GeofencingClient
private lateinit var auth: FirebaseAuth



class App : Application() {

    override fun onCreate() {
        super.onCreate()
//        Parse.initialize(
//            Parse.Configuration.Builder(this)
//                .applicationId(getString(R.string.back4app_app_id))
//                .clientKey(getString(R.string.back4app_client_key))
//                .server(getString(R.string.back4app_server_url))
//                .build()
//        )
        auth = Firebase.auth
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        geofencingClient = LocationServices.getGeofencingClient(this)


//        val currentUser : ParseUser? = ParseUser.getCurrentUser()
        val currentUser = auth.currentUser
        saveUserDataToSharedPreferences(currentUser)

        // Run periodic function
         schedulePeriodicWork(this)


        startingScreen = if (currentUser == null) {
            "LogInScreen"
        } else {
            "navbar"
        }

    }
    fun onStart() {
        val currentUser = auth.currentUser
        saveUserDataToSharedPreferences(currentUser)
    }

    private fun saveUserDataToSharedPreferences(user: FirebaseUser?) {

        val db = FirebaseFirestore.getInstance()
        val TAG = "saveUserDataToSharedPreferences"
        var severity: String? = null


        user?.uid?.let {
            db.collection("users")
                .document(user.uid)
                .get()
                .addOnSuccessListener { documentSnapshot ->
                    if (documentSnapshot.exists()) {
                        Log.d(TAG, "${documentSnapshot.id} => ${documentSnapshot.data}")
                        // Access the data using documentSnapshot.data
                        severity = documentSnapshot.data?.get("severity").toString()

                        val sharedPreferences = getSharedPreferences("UserData", Context.MODE_PRIVATE)
                        val editor = sharedPreferences.edit()

                        editor.putString("uid", user.uid)
                        editor.putString("displayName", user.displayName)
                        editor.putString("email", user.email)
                        editor.putString("severity", severity.toString())
                        // Add any other user-related data you want to store

                        editor.apply()

                    } else {
                        Log.d(TAG, "No such document")
                    }
                }
                .addOnFailureListener { exception ->
                    Log.w(TAG, "Error getting document", exception)
                }
        }


    }
}