package com.example.copd_asthma.features.utils

import android.content.Context
import android.util.Log
import androidx.compose.ui.platform.LocalContext
import com.example.copd_asthma.features.sharedPreferences.SharedPreferencesManager
import com.google.firebase.firestore.FirebaseFirestore



public fun storeGeofenceData(data: MutableMap<String, *>) {
    val db = FirebaseFirestore.getInstance()
    val collectionReference = db.collection("triggeredGeofences")

    collectionReference.add(data)
        .addOnSuccessListener { documentReference ->

            Log.d("FIRESTORE","DocumentSnapshot added with ID: ${documentReference.id}")
        }
        .addOnFailureListener { e ->
            Log.d("FIRESTORE","Error adding document: $e")
        }
}

public fun storeSeverity(severity: String, catScore: String, context: Context) {
    val db = FirebaseFirestore.getInstance()
    val collectionReference = db.collection("users")

    val sharedPrefManager = SharedPreferencesManager(context)
    val userObj = sharedPrefManager.getUserData()
    val userId = userObj.getString("uid", null)

    if (userId == null) {
        Log.d("FIRESTORE", "userId is null")
        return
    }

    val user = collectionReference.document(userId)

    user.update("severity", severity, "catScore", catScore)
        .addOnSuccessListener {
            Log.d("FIRESTORE", "DocumentSnapshot successfully updated!")
        }
        .addOnFailureListener { e ->
            Log.d("FIRESTORE", "Error updating document", e)
        }
    sharedPrefManager.syncData()
}