package com.example.copd_asthma.features.location


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.copd_asthma.features.sharedPreferences.SharedPreferencesManager
import com.example.copd_asthma.features.notification.notification
import com.example.copd_asthma.screens.createGeofenceAt
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent
import com.example.copd_asthma.features.utils.storeData

class GeofenceBroadcastReceiver : BroadcastReceiver() {

    //    @SuppressLint("MissingPermission")
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onReceive(context: Context, intent: Intent) {
        Log.d(TAG, "Geofence onReceive called! 1")
        val geofencingEvent = GeofencingEvent.fromIntent(intent)
        Log.d(TAG, "Geofence onReceive called! 2")

        if (geofencingEvent == null) {
            Log.d(TAG, "Geofence Event is null")
        }

        if (geofencingEvent != null) {
            if (geofencingEvent.hasError()) {
                Log.e(TAG, "Geofence error: ${geofencingEvent.errorCode}")
                return
            }
            Log.d(TAG, "Geofence onReceive called! 3")
        }

        val geofenceTransition = geofencingEvent?.geofenceTransition
        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {

            Log.d(TAG, "Geofence onReceive called! 4 EXIT")

            val triggeringGeofences = geofencingEvent.triggeringGeofences
            if (triggeringGeofences != null) {
                Log.d(TAG, "Geofence onReceive called! 5")

                for (geofence in triggeringGeofences) {
                    val requestId = geofence.requestId
                    Log.d(TAG, "Geofence transition detected for: $requestId")

                    GetLocation(context) { latitude, longitude ->
                        Log.d("location12", "$latitude $longitude")
                        createGeofenceAt(latitude, longitude, context)
                    }

                    val sharedPrefManager = SharedPreferencesManager(context)

                    val data = sharedPrefManager.getStoredData().all

                    val aqi = data["aqi"].toString()

                    if (aqi in listOf("1", "2", "3", "4", "5")) {
                        val createNotification = notification(context, "Bad Air Quality", "Current AQI is $aqi. Please avoid going outside.")
                        createNotification.showNotification()

//                        saveDataToFirestore(context, data)
                        storeData(data)

                    }



                }
            }
        }
        Log.d(TAG, "Geofence onReceive called! 6")

    }

    companion object {
        private const val TAG = "Geofence"
    }
}

