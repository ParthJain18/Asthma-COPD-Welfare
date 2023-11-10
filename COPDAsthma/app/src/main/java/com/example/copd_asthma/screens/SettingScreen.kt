package com.example.copd_asthma.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.copd_asthma.screens.SharedState.geofenceCount

@Composable
fun SettingScreen(padding: PaddingValues) {
    Column (verticalArrangement = Arrangement.Center){
        Text("TO Be Implemented")
        Text("Geofence count: $geofenceCount")
    }
}