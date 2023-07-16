package com.example.copd_asthma

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.parse.ParseException
import com.parse.ParseUser

fun signUp(userObj: Users, context: Context, callback: (Boolean) -> Unit) {

    val user = ParseUser()
    user.put("name", userObj.name)
    user.username = userObj.email
    user.email = userObj.email
    user.setPassword(userObj.password)
    user.put("age", userObj.age)
    user.put("age", userObj.age)
    user.put("gender", userObj.gender)
    user.put("packHistory", userObj.packHistory)
    user.put("fev1", userObj.fev1)
    user.put("fvc", userObj.fvc)
    user.put("mwt1", userObj.mwt1)
    user.put("mwt2", userObj.mwt2)
    user.put("mwtBest", userObj.mwtBest)
    user.put("severity", userObj.severity)
    user.put("diabetes", userObj.diabetes)
    user.put("hypertension", userObj.hypertension)




    user.signUpInBackground { e ->
        if (e == null) {
            Log.d("login", "done")
            Toast.makeText(context, "Success!", Toast.LENGTH_LONG).show()
            callback(true)
        } else {
            Log.d("login", "not done")
            ParseUser.logOut()
            Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
            callback(false)

        }
    }
}


fun logIn(uname: String, pword: String, context: Context, callback: (Boolean) ->Unit) {

    ParseUser.logInInBackground(uname, pword) { parseUser: ParseUser?, parseException: ParseException? ->

        if (parseUser != null) {
            Toast.makeText(context, "Successful Login. Welcome back $uname !", Toast.LENGTH_LONG).show()
            callback(true)
        } else {
            ParseUser.logOut()
            callback(false)
            if (parseException != null) {
                Toast.makeText(context, parseException.message, Toast.LENGTH_LONG).show()
            }
        }

    }
}




