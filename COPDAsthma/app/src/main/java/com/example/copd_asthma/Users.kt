package com.example.copd_asthma

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

