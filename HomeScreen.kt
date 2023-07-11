package com.example.copd_asthma.screens

import android.graphics.drawable.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController)

    {
        Scaffold(topBar =
        {
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

                },
            )
            }


    }

