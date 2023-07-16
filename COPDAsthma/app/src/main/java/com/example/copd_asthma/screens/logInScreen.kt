package com.example.copd_asthma.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.copd_asthma.R
import com.example.copd_asthma.logIn


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(modifier: Modifier = Modifier, onLogIn: () -> Unit, onSignUp: () -> Unit) {

    var userName by remember { mutableStateOf("") }
    var userPass by remember { mutableStateOf("") }

    val context = LocalContext.current




    Column(

        modifier
            .padding(all = 10.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally

        ) {

        Image(
            painter = painterResource(id = R.drawable.logo3),
            contentDescription ="header",
            modifier
                .padding(16.dp)
                .fillMaxWidth()
                .height(300.dp)
        )


        Text(
            text = "Account Details",
            modifier
                .padding(start = 15.dp, end = 10.dp, top = 10.dp)
                .fillMaxWidth(),
            fontSize = 21.sp,
            textAlign = TextAlign.Center
        )
        TextField(
            value = userName,
            onValueChange = { userName = it },
            modifier
                .padding(all = 10.dp)
                .fillMaxWidth(),
            maxLines = 1,
            label = { Text("Email") },
            colors = TextFieldDefaults.textFieldColors(containerColor = Color(233, 250, 233))
        )
        TextField(
            value = userPass,
            onValueChange = { userPass = it },
            modifier
                .padding(all = 10.dp)
                .fillMaxWidth(),
            maxLines = 1,
            label = { Text("Password") },
            colors = TextFieldDefaults.textFieldColors(containerColor = Color(233, 250, 233))

        )

        Button(

            onClick = {
                logIn(userName, userPass, context) {
                    if (it) {
                        onLogIn()
                    }
                }
            },
            modifier
                .padding(top = 30.dp)
                .width(200.dp)
        ) {
            Text(text = "Log In",
                modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                fontSize = 20.sp
            )
        }
        Text(
            text = "Forgot Password?",
            modifier
                .padding( top = 7.dp),
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter

        ) {
            Text(
                text = "Don't have an account? Join us!",
                modifier
                    .padding(top = 20.dp)
                    .clickable { onSignUp() },
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }

    }

}


@Preview(showBackground = true)
@Composable
fun Hello () {
    LogInScreen (onLogIn = { /* Handle log in action */ },
        onSignUp = { /* Handle sign up action */ })
}