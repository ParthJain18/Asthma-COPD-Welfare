package com.example.copd_asthma.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.copd_asthma.R
import com.example.copd_asthma.logIn
import com.parse.ParseException
import kotlinx.coroutines.awaitAll


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LogInScreen(modifier: Modifier = Modifier, onLogIn: () -> Unit, onSignUp: () -> Unit) {

    var userName by remember { mutableStateOf("") }
    var userPass by remember { mutableStateOf("") }

    val context = LocalContext.current




    Column(

        modifier
            .padding(all = 10.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,

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
                .width(200.dp),
            colors = ButtonDefaults.buttonColors(Color(80, 160, 96))
        ) {
            Text(text = "Log In",
                modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                fontSize = 20.sp
            )
        }
        Text(
            text = "OR",
            modifier
                .padding(start = 15.dp, end = 10.dp, top = 10.dp)
                .fillMaxWidth(),
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = "Don't have an account?",
            modifier
                .padding(start = 15.dp, end = 10.dp, top = 10.dp)
                .fillMaxWidth(),
            fontSize = 14.sp,
            textAlign = TextAlign.Center
        )

        Button(
            onClick = onSignUp,
            modifier
                .padding(top = 30.dp)
                .width(200.dp),
            colors = ButtonDefaults.buttonColors(Color(80, 160, 96))
        ) {
            Text(text = "Sign up",
                modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                fontSize = 20.sp
            )
        }





    }

}