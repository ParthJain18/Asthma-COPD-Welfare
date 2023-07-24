package com.example.copd_asthma.data




data class Users(
    val name: String,
    val email: String,
    val password: String,
    val age: Int,
    val packHistory: Int,
    val fev1: Double,
    val fvc: Double,
    val mwt1: Double,
    val mwt2: Double,
    val mwtBest: Double = maxOf(mwt1, mwt2),
    val severity: String = calculateSeverity(fev1),
    val gender: String,
    val diabetes: String,
    val hypertension: String
) {
    companion object {
        private fun calculateSeverity(fev1: Double): String {
            val thresholds = listOf(80, 50, 30, 0)
            val labels = listOf("Mild", "Moderate", "Severe", "Very Severe")

            for (i in thresholds.indices) {
                if (fev1 > thresholds[i]) {
                    return labels[i]
                }
            }
            return labels.first()
    }
}
}

