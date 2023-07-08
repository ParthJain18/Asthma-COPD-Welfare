package com.example.copd_asthma

import android.util.Log
import com.parse.ParseObject

fun saveToDB(userObj: Users) {

    val user = ParseObject("Users")
    user.put("name", userObj.name)
    userObj.hashedPassword?.let { user.put("password", it) }
    user.put("email", userObj.email)
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




    user.saveInBackground {
        if (it != null){
            it.localizedMessage?.let { message -> Log.e("MainActivity", message) }
        }else{
            Log.d("MainActivity","Object saved.")
        }
    }
}

