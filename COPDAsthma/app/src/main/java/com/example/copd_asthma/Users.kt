package com.example.copd_asthma

import android.util.Log
import com.parse.ParseObject
import org.mindrot.jbcrypt.BCrypt



data class Users (
    val name : String,
    val email : String,
    val password : String,
    val saltRounds: Int = 12,
    val hashedPassword: String? = BCrypt.hashpw(password, BCrypt.gensalt(saltRounds)),
    val age: Int,
    val packHistory: Int,
    val fev1: Int,
    val fvc: Int,
    val mwt1: Int,
    val mwt2: Int,
    val mwtBest: Int = maxOf(mwt1, mwt2),
    val severity: String = calculateSeverity(fev1),
    val gender: String,
    val diabetes: String,
    val hypertension: String
) {
    companion object {
        private fun calculateSeverity(fev1: Int): String {
            val thresholds = listOf(80, 50, 30, 0)
            val labels = listOf("Mild", "Moderate", "Severe", "Very Severe")

            for (i in thresholds.indices) {
                if (fev1 > thresholds[i]) {
                    return labels[i]
                }
            }
            return labels.last()
    }
}
}

fun saveToDB(userObj: Users) {

    val user = ParseObject("Users")
    user.put("name", userObj.name)
    userObj.hashedPassword?.let { user.put("password", it) }
    user.put("email", userObj.email)
    user.put("age", userObj.age)
    user.put("age", userObj.age)
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