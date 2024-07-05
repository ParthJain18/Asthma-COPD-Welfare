package com.example.copd_asthma.features.periodic_function

import android.content.Context
import android.util.Log
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

fun schedulePeriodicWork(context: Context) {
    try {
        val periodicWorkRequest =
            PeriodicWorkRequestBuilder<MyWorker>(16, TimeUnit.MINUTES)
                .build()
        WorkManager.getInstance(context)
            .enqueueUniquePeriodicWork(
                "PeriodicWorkRequest",
                ExistingPeriodicWorkPolicy.KEEP,
                periodicWorkRequest
            )
    } catch (e: Exception) {
        Log.d("MyWorker", "Error scheduling periodic work")
    }
}