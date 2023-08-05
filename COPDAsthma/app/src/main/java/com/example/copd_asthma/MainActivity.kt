package com.example.copd_asthma

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.copd_asthma.screens.LogInScreen
import com.example.copd_asthma.screens.NavBar
import com.example.copd_asthma.screens.SignUpScreen
import com.example.copd_asthma.ui.theme.COPDAsthmaTheme


class MainActivity : ComponentActivity() {





    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            COPDAsthmaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    AppNavigator()
                }
            }
        }
    }

}





@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = startingScreen) {
        composable("LogInScreen") {
            LogInScreen(
                onLogIn = { navController.navigate("navbar") },
                onSignUp = {navController.navigate("SignUpScreen")})
        }
        composable("SignUpScreen") {
            SignUpScreen (onNavigate = { navController.navigate("navbar") })
        }
        composable("NavBar")
             {
            NavBar( onLogOut = { navController.navigate("LogInScreen") })
        }
    }

}







@Preview(showBackground = true)
@Composable
fun Preview() {
}