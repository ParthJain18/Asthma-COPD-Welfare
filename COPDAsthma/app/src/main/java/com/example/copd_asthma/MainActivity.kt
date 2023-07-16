package com.example.copd_asthma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.copd_asthma.screens.HomeScreen
import com.example.copd_asthma.screens.LogInScreen
import com.example.copd_asthma.screens.SignUpScreen
import com.example.copd_asthma.ui.theme.COPDAsthmaTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            COPDAsthmaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigator()
                }
            }
        }
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = startingScreen) {
        composable("LogInScreen") {
            LogInScreen(
                onLogIn = { navController.navigate("HomeScreen") },
                onSignUp = {navController.navigate("SignUpScreen")})
        }
        composable("SignUpScreen") {
            SignUpScreen (onNavigate = { navController.navigate("HomeScreen") })
        }
        composable("HomeScreen") {
            HomeScreen( onLogOut = { navController.navigate("LogInScreen") })
        }
    }
}







@Preview(showBackground = true)
@Composable
fun Preview() {
}