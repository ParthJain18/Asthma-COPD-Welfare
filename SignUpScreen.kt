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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.copd_asthma.Users
import com.example.copd_asthma.signUp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(modifier: Modifier = Modifier, errorName:String = "Error Empty Field"  ,onNavigate: () -> Unit) {

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
                value = userName.trim(),
                onValueChange = { userName = it },
                modifier
                    .padding(all = 10.dp)
                    .fillMaxWidth(),
                maxLines = 1,

                        label = { Text("Full Name" ) }
            )
            if (userName==""){
            Text(text = errorName, style = TextStyle(color = Color.Red))
              }

            TextField(
                value = email.trim(),
                onValueChange = { email = it },
                modifier
                    .padding(all = 10.dp)
                    .fillMaxWidth(),
                maxLines = 1,
                label = { Text("E-mail") }
            )
            if (email==""){
                Text(text = errorName, style = TextStyle(color = Color.Red))
            }
            TextField(
                value = userPass.trim(),
                onValueChange = { userPass = it },
                modifier
                    .padding(all = 10.dp)
                    .fillMaxWidth(),
                maxLines = 1,
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation()

            )
            if (userPass==""){
                Text(text = errorName, style = TextStyle(color = Color.Red))
                }
            TextField(
                value = userAge.trim(),
                onValueChange = { userAge = it },

                modifier
                    .padding(all = 10.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                maxLines = 1,
                label = { Text("Age") }
            )
            if (userAge==""){
                Text(text = errorName, style = TextStyle(color = Color.Red))
             }
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
                            modifier = Modifier.padding(start = 16.dp),

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
                value = packHistory.trim(),
                onValueChange = { packHistory = it },
                modifier
                    .padding(all = 10.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),

                maxLines = 1,
                label = { Text("Cigarette Pack History") }
            )
            if (packHistory==""){
                Text(text = errorName, style = TextStyle(color = Color.Red))
                }
            TextField(
                value = fev1.trim(),
                onValueChange = { fev1 = it },
                modifier
                    .padding(all = 10.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),

                maxLines = 1,
                label = { Text("FEV 1") }
            )
            if (fev1==""){
                Text(text = errorName, style = TextStyle(color = Color.Red))
               }
            TextField(
                value = fvc.trim(),
                onValueChange = { fvc = it },
                modifier
                    .padding(all = 10.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),

                maxLines = 1,
                label = { Text("FVC") }
            )
            if (fvc==""){
            Text(text = errorName, style = TextStyle(color = Color.Red))
             }
            TextField(
                value = mwt1.trim(),
                onValueChange = { mwt1 = it },
                modifier
                    .padding(all = 10.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),

                maxLines = 1,
                label = { Text("MWT 1") }
            )
            if (mwt1==""){
                Text(text = errorName, style = TextStyle(color = Color.Red))
               }
            TextField(
                value = mwt2.trim(),
                onValueChange = { mwt2 = it },
                modifier
                    .padding(all = 10.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),

                maxLines = 1,
                label = { Text("MWT 2") }
            )
            if (mwt2==""){
                Text(text = errorName, style = TextStyle(color = Color.Red))
            }

            Button(
                onClick = {

                    val user = userAge.toIntOrNull()?.let {
                        fev1.toIntOrNull()?.let { it1 ->
                            packHistory.toIntOrNull()?.let { it2 ->
                                fvc.toIntOrNull()?.let { it3 ->
                                    mwt1.toIntOrNull()?.let { it4 ->
                                        mwt2.toIntOrNull()?.let { it5 ->
                                            Users(
                                                userName,
                                                email,
                                                userPass ,
                                                age = it,
                                                packHistory = it2,
                                                fev1 = it1,
                                                fvc = it3,
                                                mwt1 = it4,
                                                mwt2 = it5,
                                                gender = userGender,
                                                diabetes = userDiabetes,
                                                hypertension = userHypertension
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }



                        if (user != null) {
                            signUp(user, context) {
                                if (it) {
                                    onNavigate()
                                }


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