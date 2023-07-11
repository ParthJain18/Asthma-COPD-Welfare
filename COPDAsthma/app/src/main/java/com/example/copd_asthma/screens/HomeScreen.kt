package com.example.copd_asthma.screens

import android.graphics.Paint
import android.graphics.drawable.Icon
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.parse.ParseUser
import com.parse.SignUpCallback


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(onLogOut:()-> Unit) {
    val currentUser = ParseUser.getCurrentUser()
//    currentUser.get("Severity")

    Scaffold(
        modifier = Modifier,
        topBar =  {
            TopAppBar(
                title = {
                    Text(text = "Jetpack Compose")
                },
                navigationIcon = {
                    IconButton(onClick = { })
                    { Icon(imageVector = Icons.Rounded.Menu, contentDescription = "Drawer Icon") }
                },
                actions = {
                    IconButton(onClick = { })
                    {
                        Icon(
                            imageVector = Icons.Rounded.Search,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { contentPadding ->
        Column() {

            Text(text = "Hello! ",
                modifier = Modifier
                    .padding(contentPadding)
                .fillMaxWidth(),
            fontSize = 21.sp,
            textAlign = TextAlign.Center)

            Button(
                onClick = {
                    ParseUser.logOutInBackground()
                    onLogOut()
                }
            ){
                Text(text = "logout")
            }

        }
        // Screen content
    }
}



