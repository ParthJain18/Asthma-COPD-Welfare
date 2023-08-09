package com.example.copd_asthma.screens


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.copd_asthma.data.Users
import com.example.copd_asthma.features.authentication.signUp

import com.example.copd_asthma.ui.theme.COPDAsthmaTheme
import com.parse.ParseUser


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(modifier: Modifier, onNavigate: () -> Unit) {
    COPDAsthmaTheme {

        val currentUser = ParseUser.getCurrentUser()
        val name = currentUser.get("name").toString()
        val Fev1 = currentUser.get("fev1").toString()
        val gender = currentUser.get("gender").toString()
        val age = currentUser.get("age").toString()
        val diabetes = currentUser.get("diabetes").toString()
        val hyper = currentUser.get("hypertension").toString()
        val Mwt1 = currentUser.get("mwt1").toString()
        val Mwt2 = currentUser.get("mwt2").toString()
        val Fvc = currentUser.get("fvc").toString()
        val PackHistory = currentUser.get("packHistory").toString()
        val Email = currentUser.get("email").toString()
        val pass = currentUser.get("password").toString()
        val cat = currentUser.get("cat")
        val sgrq = currentUser.get("sgrq")


//        var name = "ninad"
//        var Email = "123@123"
//        var pass = "123123"
//        var age = "18"
//        var PackHistory = "5"
//        var Fev1 = "79"
//        var Fvc = "45"
//        var Mwt1 = "4"
//        var Mwt2 = "5"
//        var gender = "male"
//        var diabetes = "yes"
//        var hyper = "yes"

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
        var userCat by remember { mutableStateOf(cat) }
        var userSGRQ by remember { mutableStateOf(sgrq) }
        val context = LocalContext.current
        var uNameenabled by remember { mutableStateOf(false)}
        var uEmailenabled by remember { mutableStateOf(false)}
        var uPassenabled by remember { mutableStateOf(false)}
        var uAgeenabled by remember { mutableStateOf(false)}
        var uCPVenabled by remember { mutableStateOf(false)}
        var uFEV1enabled by remember { mutableStateOf(false)}
        var uFVCenabled by remember { mutableStateOf(false)}
        var uMWT1enabled by remember { mutableStateOf(false)}
        var uMWT2enabled by remember { mutableStateOf(false)}




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
                    text = "Profile Page",
                    modifier
                        .padding(start = 15.dp, end = 10.dp, top = 10.dp)
                        .fillMaxWidth(),
                    fontSize = 36.sp,
                    textAlign = TextAlign.Center

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
                    enabled =uNameenabled,


//                    shape = RoundedCornerShape(36.dp) ,
                     trailingIcon = {
                                    IconButton(onClick = { uNameenabled=true}) {
                                        Icon(imageVector = Icons.Filled.Edit, contentDescription = "Click to Edit")
                                        
                                    }
                     },

                    label = { Text("Full Name") }
                )


                TextField(
                    value = email.trim(),
                    onValueChange = { email = it },
                    modifier
                        .padding(all = 10.dp)
                        .fillMaxWidth(),
                    maxLines = 1,
//                    shape = RoundedCornerShape(36.dp) ,
                    enabled=uEmailenabled,
                    trailingIcon = {
                                   IconButton(onClick = {uEmailenabled=true}) {
                                       Icon(imageVector = Icons.Filled.Edit, contentDescription = "Click to Edit")
                                   }
                    },

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

                    enabled  =uPassenabled,

                    trailingIcon = {
                        IconButton(onClick = {uPassenabled=true}) {
                            Icon(imageVector = Icons.Filled.Edit, contentDescription = "Click to Edit")
                        }
                    },


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
//                    shape = RoundedCornerShape(36.dp) ,
                    enabled=uAgeenabled,
                    trailingIcon = {
                        IconButton(onClick = { uAgeenabled=true }) {
                            Icon(imageVector = Icons.Filled.Edit, contentDescription = "Click to Edit")
                        }
                    },


                    label = { Text("Age") }
                )



                Text(
                    text = "Gender:",
                    modifier
                        .padding(start = 15.dp, end = 10.dp, top = 10.dp)
                        .fillMaxWidth(),
                    fontSize = 21.sp,
                    textAlign = TextAlign.Left
                )


                val gender = listOf("Male", "Female", "Other")
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
                                onClick = { onOptionSelect(text) }
                            )
                            Text(
                                text = text,
                                modifier = Modifier.padding(start = 16.dp)
                            )
                        }
                    }
                }
                Text(
                    text = "Diabetes:",
                    modifier
                        .padding(start = 15.dp, end = 10.dp, top = 10.dp)
                        .fillMaxWidth(),
                    fontSize = 21.sp,
                    textAlign = TextAlign.Left
                )

                val diabetes = listOf("Yes", "No")
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
                    text = "HyperTention:",
                    modifier
                        .padding(start = 15.dp, end = 10.dp, top = 10.dp)
                        .fillMaxWidth(),
                    fontSize = 21.sp,
                    textAlign = TextAlign.Left
                )

                val hypertension = listOf("Yes", "No")
                val (hyperSelect, onHyperSelect) = remember { mutableStateOf("") }
                Column {
                    hypertension.forEach { text ->
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
                                onClick = { onHyperSelect(text) }
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
//                    shape = RoundedCornerShape(36.dp) ,

                    maxLines = 1,
                    enabled=uCPVenabled,
                    trailingIcon = {
                        IconButton(onClick = { uCPVenabled=true }) {
                            Icon(imageVector = Icons.Filled.Edit, contentDescription = "Click to Edit")
                        }
                    },

                    label = { Text("Cigarette Pack History") }
                )

                TextField(
                    value = fev1.trim(),
                    onValueChange = { fev1 = it },
                    modifier
                        .padding(all = 10.dp)
                        .fillMaxWidth(),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
//                    shape = RoundedCornerShape(36.dp) ,

                    maxLines = 1,
                    enabled=uFEV1enabled,
                    trailingIcon = {
                        IconButton(onClick = { uFEV1enabled=true }) {
                            Icon(imageVector = Icons.Filled.Edit, contentDescription = "Click to Edit")
                        }
                    },
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
//                    shape = RoundedCornerShape(36.dp) ,
                    enabled=uFVCenabled,
                    trailingIcon = {
                        IconButton(onClick = { uFVCenabled=true }) {
                            Icon(imageVector = Icons.Filled.Edit, contentDescription = "Click to Edit")
                        }
                    },

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
//                    shape = RoundedCornerShape(36.dp) ,
                    enabled=uMWT1enabled,
                    trailingIcon = {
                        IconButton(onClick = { uMWT1enabled=true }) {
                            Icon(imageVector = Icons.Filled.Edit, contentDescription = "Click to Edit")
                        }
                    },

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
//                    shape = RoundedCornerShape(36.dp) ,
                    enabled=uMWT2enabled,
                    trailingIcon = {
                        IconButton(onClick = { uMWT2enabled=true }) {
                            Icon(imageVector = Icons.Filled.Edit, contentDescription = "Click to Edit")
                        }
                    },
//                    shape = RoundedCornerShape(15.dp) ,


                    label = { Text("MWT 2") }


                )


                Button(
                    onClick = {

                        val user = userAge.toDouble().let {
                            fev1.toDouble().let { it1 ->
                                packHistory.toIntOrNull()?.let { it2 ->
                                    fvc.toDouble().let { it3 ->
                                        mwt1.toDouble().let { it4 ->
                                            mwt2.toDouble().let { it5 ->
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
                                                    hypertension = userHypertension,
                                                    cat = userCat as Double,
                                                    sgrq = userSGRQ as Double
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
    ProfileScreen (modifier = Modifier, onNavigate = { })
}
