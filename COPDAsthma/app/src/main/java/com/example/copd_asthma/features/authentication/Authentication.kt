package com.example.copd_asthma.features.authentication

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.example.copd_asthma.data.Users
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore


private lateinit var auth: FirebaseAuth

fun signUp(userObj: Users, context: Context, callback: (Boolean) -> Unit) {
    val TAG = "signUp"
    val auth = Firebase.auth
    val db = FirebaseFirestore.getInstance()

    Log.d(TAG, "signUp: ${userObj.email}")

    // Validate email and password
    if (userObj.email.isEmpty() || userObj.password.isEmpty()) {
        Toast.makeText(
            context,
            "Email or password is empty",
            Toast.LENGTH_SHORT,
        ).show()
        callback(false)
        return
    }

    auth.createUserWithEmailAndPassword(userObj.email, userObj.password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser

                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(userObj.name)
                    .build()

                user?.updateProfile(profileUpdates)
                    ?.addOnCompleteListener { profileUpdateTask ->
                        if (profileUpdateTask.isSuccessful) {
                            db.collection("users").document(user.uid)
                                .set(mapOf(
                                    "severity" to userObj.userLungHealth,
                                    "userId" to user.uid,
                                    "name" to userObj.name,
                                    "email" to userObj.email
                                ))
                                .addOnSuccessListener {
                                    Log.d(TAG, "User profile and metadata updated.")
                                    val sharedPrefManager = SharedPreferencesManager(context)
                                    sharedPrefManager.syncData()
                                    callback(true)
                                }
                                .addOnFailureListener {
                                    Log.w(TAG, "Failed to update user metadata in Firestore.", it)
                                    callback(false)
                                }
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                            Toast.makeText(
                                context,
                                "Authentication failed.",
                                Toast.LENGTH_SHORT,
                            ).show()
                            callback(false)
                        }
                    }
            } else {
                Log.w(TAG, task.exception?.message ?: "Authentication Failed", task.exception)
                Toast.makeText(
                    context,
                    task.exception?.message ?: "Authentication Failed",
                    Toast.LENGTH_LONG,
                ).show()

                callback(false)
            }
        }
}







//    user.put("age", userObj.age)
//    user.put("age", userObj.age)
//    user.put("gender", userObj.gender)
//    user.put("packHistory", userObj.packHistory)
//    user.put("fev1", userObj.fev1)
//    user.put("fvc", userObj.fvc)
//    user.put("mwt1", userObj.mwt1)
//    user.put("mwt2", userObj.mwt2)
//    user.put("mwtBest", userObj.mwtBest)
//    user.put("severity", userObj.severity)
//    user.put("diabetes", userObj.diabetes)
//    user.put("hypertension", userObj.hypertension)


//    user.signUpInBackground { e ->
//        if (e == null) {
//            Log.d("login", "done")
//            Toast.makeText(context, "Success!", Toast.LENGTH_LONG).show()
//            callback(true)
//        } else {
//            Log.d("login", "not done")
//            ParseUser.logOut()
//            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
//            callback(false)
//
//        }
//





fun logIn(uname: String, pword: String, context: Context, callback: (Boolean) ->Unit) {

    val TAG = "logIn"
    auth = Firebase.auth


    auth.signInWithEmailAndPassword(uname, pword)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Log.d(TAG, "signInWithEmail:success")
                val user = auth.currentUser
                val sharedPrefManager = SharedPreferencesManager(context)
                sharedPrefManager.syncData()

                callback(true)
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "signInWithEmail:failure", task.exception)
                Toast.makeText(
                    context,
                    "Authentication failed.",
                    Toast.LENGTH_SHORT,
                ).show()
                callback(false)
            }
        }
}
//    ParseUser.logInInBackground(uname, pword) { parseUser: ParseUser?, parseException: ParseException? ->
//
//        if (parseUser != null) {
//            Toast.makeText(context, "Successful Login. Welcome back $uname !", Toast.LENGTH_LONG).show()
//            callback(true)
//        } else {
//            ParseUser.logOut()
//            callback(false)
//            if (parseException != null) {
//                Toast.makeText(context, parseException.message, Toast.LENGTH_LONG).show()
//            }
//        }
//
//    }
//}




