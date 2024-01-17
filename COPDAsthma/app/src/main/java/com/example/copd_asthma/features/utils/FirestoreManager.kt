package com.example.copd_asthma.features.utils

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore



public fun storeData(data: MutableMap<String, *>) {
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