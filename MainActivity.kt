package com.example.copd_asthma

import android.os.Build
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import android.window.SplashScreen
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.copd_asthma.screens.LogInScreen
import com.example.copd_asthma.screens.NavBar
import com.example.copd_asthma.screens.SignUpScreen
import com.example.copd_asthma.ui.theme.COPDAsthmaTheme
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.Q)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            COPDAsthmaTheme {
                // A surface container using the 'background' color from the theme
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
        //startdestination splash screen kiya hai and niche composable add kiya hai
    NavHost(navController, startDestination = "splash_screen") {
        composable("splash_screen") {
            SplashScreen(navController = navController)
        }

        composable("LogInScreen") {
            LogInScreen(
                onLogIn = { navController.navigate("NavBar") },
                onSignUp = {navController.navigate("SignUpScreen")})
        }
        composable("SignUpScreen") {
            SignUpScreen (onNavigate = { navController.navigate("NavBar") })
        }
        composable("NavBar")
             {
            NavBar( onLogOut = { navController.navigate("LogInScreen") })
        }

    }

}
//splash screen function
@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }

    // Animation
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 0.7f,
            // tween Animation
            animationSpec = tween(
                durationMillis = 800,
                easing = {
                    OvershootInterpolator(4f).getInterpolation(it)
                }))
        // Customize the delay time
        delay(3000L)
        navController.navigate("main_screen")
    }

    // Image
    Box(contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()) {
        // Change the logo
        Image(painter = painterResource(id = R.drawable.logo2),
            contentDescription = "Logo",
            modifier = Modifier.scale(scale.value))
    }
}







@Preview(showBackground = true)
@Composable
fun Preview() {
}