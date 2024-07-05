package com.example.copd_asthma.features.periodic_function
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.copd_asthma.features.location.GetLocation
import com.example.copd_asthma.screens.createGeofenceAt
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

class MyWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    override fun doWork(): Result {
        performTask()
        return Result.success()
    }

    private fun performTask() {
        // The function you want to run periodically
        Log.d("MyWorker", "Running periodic function")

        if (hasBackgroundLocationPermission()) {
            try {
                GetLocation(applicationContext) { latitude, longitude ->
                    Log.d("location12", "$latitude $longitude")
                    createGeofenceAt(latitude, longitude, applicationContext)
                }
            } catch (e: SecurityException) {
                Log.d("permissions", e.toString())
            }
        } else {
            Log.d("permissions", "Background location permission is not granted")
        }
    }
    private fun hasBackgroundLocationPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            ContextCompat.checkSelfPermission(applicationContext, Manifest.permission.ACCESS_BACKGROUND_LOCATION) == PackageManager.PERMISSION_GRANTED
        } else {
            true // For Android 9 (Pie) and below, background location permission is not needed
        }
    }
}