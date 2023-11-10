package com.example.copd_asthma.features.authentication

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class SharedPreferencesManager(private val context: Context) {

    private val auth = FirebaseAuth.getInstance()

    private val user = auth.currentUser

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("UserData", Context.MODE_PRIVATE)

    fun clearPreferences() {
        sharedPreferences.edit().clear().apply()
    }
    fun getUserData(): SharedPreferences {
        return context.getSharedPreferences("UserData", Context.MODE_PRIVATE)
    }
    fun syncData() {

        val db = FirebaseFirestore.getInstance()
        val TAG = "syncData"
        var severity: String? = null


        user?.uid?.let {
            db.collection("users")
                .document(user.uid ?: "")
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
}