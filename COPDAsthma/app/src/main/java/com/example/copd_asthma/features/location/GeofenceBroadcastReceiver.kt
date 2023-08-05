package com.example.copd_asthma.features.location


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.copd_asthma.features.notification.notification
import com.example.copd_asthma.screens.createGeofenceAt
import com.google.android.gms.location.Geofence
import com.google.android.gms.location.GeofencingEvent

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
                    // Perform the desired action here, like showing a notification, etc.
                    //TODO: Notification trigger kar

                    val createNotification = notification(context, "My title", "This is the content of notification. It's not so important right now!")
                    createNotification.showNotification()
                    GetLocation(context) { latitude, longitude ->
                        Log.d("location12", "$latitude $longitude")
                        createGeofenceAt(latitude, longitude, context)
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
