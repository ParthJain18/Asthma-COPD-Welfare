package com.example.copd_asthma.features.authentication

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import java.sql.Timestamp

class SharedPreferencesManager(private val context: Context) {

    private val auth = FirebaseAuth.getInstance()

    private val user = auth.currentUser

    private val settings = context.getSharedPreferences("Settings", Context.MODE_PRIVATE)

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("UserData", Context.MODE_PRIVATE)

    private val dataSharedPref = context.getSharedPreferences("DataToStore", Context.MODE_PRIVATE)

    private val db = FirebaseFirestore.getInstance()




    fun clearPreferences() {
        sharedPreferences.edit().clear().apply()
        dataSharedPref.edit().clear().apply()
    }
    fun getUserData(): SharedPreferences {
        return context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
    }

    fun getStoredData(): SharedPreferences {
        val time = Timestamp(System.currentTimeMillis()).toString()
        Log.d("time", time)
        storeData(time = time)

        return context.getSharedPreferences("DataToStore", Context.MODE_PRIVATE)
    }
    fun syncData() {

        val TAG = "syncData"
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

    fun storeData(uid: String? = null,
                  email: String? = null,
                  severity: String? = null,
                  coords: List<Double>? = null,
                  city: String? = null,
                  aqi: String? = null,
                  safety: String? = null,
                  time: String? = null,
                  airQualityName: String? = null) {
        val editor = dataSharedPref.edit()

        editor.putString("uid", uid ?: dataSharedPref.getString("uid", ""))
        editor.putString("email", email ?: dataSharedPref.getString("email", ""))
        editor.putString("severity", severity ?: dataSharedPref.getString("severity", ""))
        editor.putString("city", city ?: dataSharedPref.getString("city", ""))
        editor.putString("aqi", aqi ?: dataSharedPref.getString("aqi", ""))
        editor.putString("lat", coords?.getOrNull(0)?.toString() ?: dataSharedPref.getString("lat", ""))
        editor.putString("lon", coords?.getOrNull(1)?.toString() ?: dataSharedPref.getString("lon", ""))
        editor.putString("safety", safety ?: dataSharedPref.getString("safety", ""))
        editor.putString("time", time ?: dataSharedPref.getString("time", ""))
        editor.putString("airQualityName", airQualityName ?: dataSharedPref.getString("airQualityName", ""))

        editor.apply()
    }

    fun setSettings(
        severity: String? = null,
        radius: String? = null,
        ) {
        val editor = settings.edit()
        editor.putString("severity", severity ?: settings.getString("severity", ""))
        editor.putString("radius", radius ?: settings.getString("radius", ""))
        editor.apply()

        storeData(severity = severity)

        user?.uid?.let {
            db.collection("users")
                .document(user.uid)
                .set(
                    hashMapOf(
                        "severity" to severity
                    ),
                    SetOptions.merge()
                )
        }

        syncData()



    }

    fun getSettings(): SharedPreferences {
        return context.getSharedPreferences("Settings", Context.MODE_PRIVATE)
    }

}