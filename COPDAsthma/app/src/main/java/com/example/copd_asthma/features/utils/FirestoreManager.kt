package com.example.copd_asthma.features.utils

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreManager {

    private val db = FirebaseFirestore.getInstance()
    private val collectionReference = db.collection("triggeredGeofences")

    fun storeData(data: MutableMap<String, *>) {
        collectionReference.add(data)
            .addOnSuccessListener { documentReference ->

                Log.d("FIRESTORE","DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.d("FIRESTORE","Error adding document: $e")
            }
    }
}