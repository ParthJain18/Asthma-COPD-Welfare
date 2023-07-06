package com.example.copd_asthma

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.copd_asthma.ui.theme.COPDAsthmaTheme
import com.parse.Parse
import com.parse.ParseObject


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            COPDAsthmaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "StartScreen") {
        composable("StartScreen") {
            StartScreen(
                onLogIn = { navController.navigate("HomeScreen") },
                onSignUp = {navController.navigate("SignUpScreen")})
        }
        composable("SignUpScreen") {
            SignUpScreen (onNavigate = { navController.navigate("HomeScreen") } )
        }
        composable("HomeScreen") {
            HomeScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StartScreen(modifier: Modifier = Modifier, onLogIn: () -> Unit, onSignUp: () -> Unit) {

    var userName by remember { mutableStateOf("") }
    var userPass by remember { mutableStateOf("") }




    Column(

        modifier.padding(all = 10.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,

        ) {

        Image(
            painter = painterResource(id = R.drawable.logo2),
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
            label = {Text("User Name")}
        )
        TextField(
            value = userPass,
            onValueChange = { userPass = it },
            modifier
                .padding(all = 10.dp)
                .fillMaxWidth(),
            maxLines = 1,
            label = {Text("Password")}
        )

        Button(
            onClick = onLogIn,
            modifier.padding(top = 30.dp).width(200.dp),
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
            modifier.padding(top = 30.dp).width(200.dp),
            colors = ButtonDefaults.buttonColors(Color(80, 160, 96))
        ) {
            Text(text = "Sign up",
                modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                fontSize = 20.sp
            )
        }





    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(modifier: Modifier = Modifier, onNavigate: () -> Unit) {

    var userName by remember { mutableStateOf("") }
    var userPass by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var packHistory by remember { mutableStateOf("") }
    var fev1 by remember { mutableStateOf("") }
    var fvc by remember { mutableStateOf("") }
    var mwt1 by remember { mutableStateOf("") }
    var mwt2 by remember { mutableStateOf("") }
    var mwtBest by remember { mutableStateOf("") }


    Column(

        modifier.padding(all = 10.dp)
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
            textAlign = TextAlign.Center
        )
        TextField(
            value = userName,
            onValueChange = { userName = it },
            modifier
                .padding(all = 10.dp)
                .fillMaxWidth(),
            maxLines = 1,
            label = {Text("User Name")}
        )
        TextField(
            value = userPass,
            onValueChange = { userPass = it },
            modifier
                .padding(all = 10.dp)
                .fillMaxWidth(),
            maxLines = 1,
            label = {Text("Password")}
        )
        TextField(
            value = age,
            onValueChange = { age = it },
            modifier
                .padding(all = 10.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),

            maxLines = 1,
            label = {Text("Age")}
        )
        TextField(
            value = packHistory,
            onValueChange = { packHistory = it },
            modifier
                .padding(all = 10.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),

            maxLines = 1,
            label = {Text("Cigarette Pack History")}
        )

        TextField(
            value = fev1,
            onValueChange = { fev1 = it },
            modifier
                .padding(all = 10.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),

            maxLines = 1,
            label = {Text("FEV 1")}
        )
        TextField(
            value = fvc,
            onValueChange = { fvc = it },
            modifier
                .padding(all = 10.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),

            maxLines = 1,
            label = {Text("FVC")}
        )
        TextField(
            value = mwt1,
            onValueChange = { mwt1 = it },
            modifier
                .padding(all = 10.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),

            maxLines = 1,
            label = {Text("MWT 1")}
        )
        TextField(
            value = mwt2,
            onValueChange = { mwt2 = it },
            modifier
                .padding(all = 10.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),

            maxLines = 1,
            label = {Text("MWT2")}
        )
        TextField(
            value = mwtBest,
            onValueChange = { mwtBest = it },
            modifier
                .padding(all = 10.dp)
                .fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),

            maxLines = 1,
            label = {Text("MWT Best")}


        )
        Text(
            text = "Enter Your COPD Severity:",
            modifier
                .padding(start = 15.dp, end = 10.dp, top = 10.dp)
                .fillMaxWidth(),
            fontSize = 21.sp,
            textAlign = TextAlign.Left
        )
        val radioOptions = listOf("Mild", "Moderate", "Severe","Very Severe")
        val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1] ) }
        Column {
            radioOptions.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = {
                                onOptionSelected(text)
                            }
                        )
                        .padding(horizontal = 16.dp)
                        .padding(vertical = 5.dp)
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = { onOptionSelected(text) }
                    )
                    Text(
                        text = text,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }

        Text(
            text = "Enter Your Gender:",
            modifier
                .padding(start = 15.dp, end = 10.dp, top = 10.dp)
                .fillMaxWidth(),
            fontSize = 21.sp,
            textAlign = TextAlign.Left
        )

        val gender = listOf("Male","Female")
        val (selected, onOptionSelect) = remember { mutableStateOf(gender[1] ) }
        Column {
            gender.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (text == selected),
                            onClick = {
                                onOptionSelect(text)
                            }
                        )
                        .padding(horizontal = 16.dp)
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
            text = "Do you have Diabetes?:",
            modifier
                .padding(start = 15.dp, end = 10.dp, top = 10.dp)
                .fillMaxWidth(),
            fontSize = 21.sp,
            textAlign = TextAlign.Left
        )

        val diabetes = listOf("Yes","No")
        val (diabetesSelect, onDiabetesSelect) = remember { mutableStateOf(diabetes[1] ) }
        Column {
            diabetes.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (text == selected),
                            onClick = {
                                onDiabetesSelect(text)
                            }
                        )
                        .padding(horizontal = 16.dp)
                ) {
                    RadioButton(
                        selected = (text == selected),
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
        val (hyperSelect, onHyperSelect) = remember { mutableStateOf(hypertention[1] ) }
        Column {
            hypertention.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (text == selected),
                            onClick = {
                                onHyperSelect(text)
                            }
                        )
                        .padding(horizontal = 16.dp)
                ) {
                    RadioButton(
                        selected = (text == selected),
                        onClick = { onHyperSelect(text) }
                    )
                    Text(
                        text = text,

                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }




        Button(
            onClick = onNavigate,
            modifier.padding(top = 30.dp).width(200.dp),
            colors = ButtonDefaults.buttonColors(Color(80, 160, 96))
        ) {
            Text(text = "Sign up",
                modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                fontSize = 20.sp
            )
        }


    }

}

@Composable
fun HomeScreen() {
    Text ("In Progress")
}
@Preview(showBackground = true)
@Composable
fun Preview() {
    SignUpScreen {}
}