package com.example.copd_asthma.features.periodic_function
import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.copd_asthma.features.location.GetLocation
import com.example.copd_asthma.screens.createGeofenceAt

class MyWorker(appContext: Context, workerParams: WorkerParameters) :
    Worker(appContext, workerParams) {

    override fun doWork(): Result {
        performTask()
        return Result.success()
    }

    private fun performTask() {
        // The function you want to run periodically
        Log.d("MyWorker", "Running periodic function")

        GetLocation(applicationContext) { latitude, longitude ->
            Log.d("location12", "$latitude $longitude")
            createGeofenceAt(latitude, longitude, applicationContext)
        }
    }
}