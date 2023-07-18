package com.example.copd_asthma.screens


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.copd_asthma.Users
import com.example.copd_asthma.signUp

import com.example.copd_asthma.ui.theme.COPDAsthmaTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(modifier: Modifier = Modifier, onNavigate: () -> Unit) {
    COPDAsthmaTheme {

        //val currentUser = ParseUser.getCurrentUser()
        //val name = currentUser.get("name").toString()
        //val Fev1 = currentUser.get("fev1").toString()
        //val gender = currentUser.get("gender").toString()
        //val age = currentUser.get("age").toString()
        //val diabetes = currentUser.get("diabetes").toString()
        //val hyper = currentUser.get("hypertension").toString()
        //val Mwt1 = currentUser.get("mwt1").toString()
        //val Mwt2 = currentUser.get("mwt2").toString()
        //val Fvc = currentUser.get("fvc").toString()
        //val PackHistory = currentUser.get("packHistory").toString()
        //val Email = currentUser.get("email").toString()
        //val pass = currentUser.get("password").toString()

        var name = "ninad"
        var Email = "123@123"
        var pass = "123123"
        var age = "18"
        var PackHistory = "5"
        var Fev1 = "79"
        var Fvc = "45"
        var Mwt1 = "4"
        var Mwt2 = "5"
        var gender = "male"
        var diabetes = "yes"
        var hyper = "yes"

        var userName by remember { mutableStateOf(name) }
        var email by remember { mutableStateOf(Email) }
        var userPass by remember { mutableStateOf(pass) }
        var userAge by remember { mutableStateOf(age) }
        var packHistory by remember { mutableStateOf(PackHistory) }
        var fev1 by remember { mutableStateOf(Fev1) }
        var fvc by remember { mutableStateOf(Fvc) }
        var mwt1 by remember { mutableStateOf(Mwt1) }
        var mwt2 by remember { mutableStateOf(Mwt2) }
        var userGender by remember { mutableStateOf(gender) }
        var userDiabetes by remember { mutableStateOf(diabetes) }
        var userHypertension by remember { mutableStateOf(hyper) }

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
                    text = "Current User Details:",
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

                    label = { Text("Full Name") }
                )


                TextField(
                    value = email.trim(),
                    onValueChange = { email = it },
                    modifier
                        .padding(all = 10.dp)
                        .fillMaxWidth(),
                    maxLines = 1,
                    label = { Text("E-mail") }
                )

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

                TextField(
                    value = userAge.trim(),
                    onValueChange = { userAge = it },
                    modifier
                        .padding(all = 10.dp)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),

                    maxLines = 1,
                    label = { Text("Age?") }
                )

                TextField(
                    value = userDiabetes.trim(),
                    onValueChange = { userDiabetes = it },
                    modifier
                        .padding(all = 10.dp)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),

                    maxLines = 1,
                    label = { Text("Diabetes?") }
                )
                TextField(
                    value = userHypertension.trim(),
                    onValueChange = { userHypertension = it },
                    modifier
                        .padding(all = 10.dp)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),

                    maxLines = 1,
                    label = { Text("Hypertension?") }
                )

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

                TextField(
                    value = mwt2,
                    onValueChange = {
                        mwt2 = it
                    },

                    modifier
                        .padding(all = 10.dp)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),

                    maxLines = 1,

                    label = { Text("MWT 2") }


                )


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
                                                    userPass,
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
                        else
                        {
                            Toast.makeText(context, "Error Empty fields present!!", Toast.LENGTH_LONG).show()
                        }


                    },
                    modifier
                        .padding(top = 30.dp)
                        .width(200.dp)
                ) {
                    Text(
                        text = "Update Profile",
                        modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                        fontSize = 20.sp
                    )}

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    ProfileScreen {}
}



