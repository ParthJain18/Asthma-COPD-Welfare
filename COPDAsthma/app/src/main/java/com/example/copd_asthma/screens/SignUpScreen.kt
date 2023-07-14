package com.example.copd_asthma.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.copd_asthma.Users
import com.example.copd_asthma.signUp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(modifier: Modifier = Modifier, onNavigate: () -> Unit) {

    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var userPass by remember { mutableStateOf("") }
    var userAge by remember { mutableStateOf("") }
    var packHistory by remember { mutableStateOf("") }
    var fev1 by remember { mutableStateOf("") }
    var fvc by remember { mutableStateOf("") }
    var mwt1 by remember { mutableStateOf("") }
    var mwt2 by remember { mutableStateOf("") }
    var userGender by remember { mutableStateOf("") }
    var userDiabetes by remember { mutableStateOf("") }
    var userHypertension by remember { mutableStateOf("") }

    val context = LocalContext.current



    Surface(
        modifier = Modifier.padding(5.dp)
    ) {

        Column(

            modifier
                .padding(all = 10.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,

            ) {



            Text(
                text = "Please Enter Your Spirometry Results:",
                modifier
                    .padding(start = 15.dp, end = 10.dp, top = 10.dp)
                    .fillMaxWidth(),
                fontSize = 21.sp,
                textAlign = TextAlign.Left
            )
            Spacer(
                modifier.heightIn(50.dp)
            )
            TextField(
                value = userName,
                onValueChange = { userName = it },
                modifier
                    .padding(all = 10.dp)
                    .fillMaxWidth(),
                maxLines = 1,
                label = { Text("Full Name") }
            )
            TextField(
                value = email,
                onValueChange = { email = it },
                modifier
                    .padding(all = 10.dp)
                    .fillMaxWidth(),
                maxLines = 1,
                label = { Text("E-mail") }
            )
            TextField(
                value = userPass,
                onValueChange = { userPass = it },
                modifier
                    .padding(all = 10.dp)
                    .fillMaxWidth(),
                maxLines = 1,
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation()
            )
            TextField(
                value = userAge,
                onValueChange = { userAge = it },

                modifier
                    .padding(all = 10.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                maxLines = 1,
                label = { Text("Age") }
            )
            Text(
                text = "Enter Your Gender:",
                modifier
                    .padding(start = 15.dp, end = 10.dp, top = 10.dp)
                    .fillMaxWidth(),
                fontSize = 21.sp,
                textAlign = TextAlign.Left
            )

            val gender = listOf("Male","Female", "other")
            val (selected, onOptionSelect) = remember { mutableStateOf("") }
            Column {
                gender.forEach { text ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (text == selected),
                                onClick = {
                                    onOptionSelect(text)
                                    userGender = text

                                }
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        RadioButton(
                            selected = (text == selected),
                            onClick = {
                                onOptionSelect(text)
                            }
                        )
                        Text(
                            text = text,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }
            Text(
                text = "Do you have Diabetes?:",
                modifier
                    .padding(start = 15.dp, end = 10.dp, top = 10.dp)
                    .fillMaxWidth(),
                fontSize = 21.sp,
                textAlign = TextAlign.Left
            )

            val diabetes = listOf("Yes","No")
            val (diabetesSelect, onDiabetesSelect) = remember { mutableStateOf("") }
            Column {
                diabetes.forEach { text ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (text == diabetesSelect),
                                onClick = {
                                    onDiabetesSelect(text)
                                    userDiabetes = text

                                }
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        RadioButton(
                            selected = (text == diabetesSelect),
                            onClick = { onDiabetesSelect(text) }
                        )
                        Text(
                            text = text,

                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }
            Text(
                text = "Do you have Hypertension?:",
                modifier
                    .padding(start = 15.dp, end = 10.dp, top = 10.dp)
                    .fillMaxWidth(),
                fontSize = 21.sp,
                textAlign = TextAlign.Left
            )

            val hypertention = listOf("Yes","No")
            val (hyperSelect, onHyperSelect) = remember { mutableStateOf("") }
            Column {
                hypertention.forEach { text ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (text == hyperSelect),
                                onClick = {
                                    onHyperSelect(text)
                                    userHypertension = text
                                }
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically


                    ) {
                        RadioButton(
                            selected = (text == hyperSelect),
                            onClick = {
                                onHyperSelect(text)
                            }
                        )
                        Text(
                            text = text,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                }
            }


            TextField(
                value = packHistory,
                onValueChange = { packHistory = it },
                modifier
                    .padding(all = 10.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),

                maxLines = 1,
                label = { Text("Cigarette Pack History") }
            )

            TextField(
                value = fev1,
                onValueChange = { fev1 = it },
                modifier
                    .padding(all = 10.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),

                maxLines = 1,
                label = { Text("FEV 1") }
            )
            TextField(
                value = fvc,
                onValueChange = { fvc = it },
                modifier
                    .padding(all = 10.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),

                maxLines = 1,
                label = { Text("FVC") }
            )
            TextField(
                value = mwt1,
                onValueChange = { mwt1 = it },
                modifier
                    .padding(all = 10.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),

                maxLines = 1,
                label = { Text("MWT 1") }
            )
            TextField(
                value = mwt2,
                onValueChange = { mwt2 = it },
                modifier
                    .padding(all = 10.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),

                maxLines = 1,
                label = { Text("MWT 2") }
            )

            Button(
                onClick = {
                    val user = Users(
                        userName,
                        email,
                        userPass ,
                        age = userAge.toInt(),
                        packHistory = packHistory.toInt(),
                        fev1 = fev1.toInt(),
                        fvc = fvc.toInt(),
                        mwt1 = mwt1.toInt(),
                        mwt2 = mwt2.toInt(),
                        gender = userGender,
                        diabetes = userDiabetes,
                        hypertension = userHypertension
                    )
                    signUp(user, context) {
                        if (it) {
                            onNavigate()
                        }
                    }
                },
                modifier
                    .padding(top = 30.dp)
                    .width(200.dp)
            ) {
                Text(text = "Sign up",
                    modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                    fontSize = 20.sp
                )
            }


        }
    }

}