package com.example.copd_asthma.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.Dialog
import com.example.copd_asthma.features.predictionModel.predictCOPDSeverity
import com.example.copd_asthma.features.utils.storeSeverity


data class FormState(
    var age: String = "",
    var smoking: String = "0",
    var packHistory: String = "",
    var fev1: String = "",
    var fvc: String = "",
    var fev1Fvc: String = "",
    var cat: String = "",
    var sgrq: String = "",
    var gender: String = "0",
    var diabetes: String = "0",
    var hypertension: String = "0",
    var mwt1: String = "",
    var mwt2: String = "",
    var mwt1Best: String = ""
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputForm(formState: FormState, onNavigate: () -> Unit = {}) {
    // Declare mutableStateOf variables directly here
    var age by remember { mutableStateOf(formState.age) }
    var smoking by remember { mutableStateOf(formState.smoking) }
    var packHistory by remember { mutableStateOf(formState.packHistory) }
    var fev1 by remember { mutableStateOf(formState.fev1) }
    var fvc by remember { mutableStateOf(formState.fvc) }
    var fev1Fvc by remember { mutableStateOf(formState.fev1Fvc) }
    var cat by remember { mutableStateOf(formState.cat) }
    var sgrq by remember { mutableStateOf(formState.sgrq) }
    var gender by remember { mutableStateOf(formState.gender) }
    var diabetes by remember { mutableStateOf(formState.diabetes) }
    var hypertension by remember { mutableStateOf(formState.hypertension) }
    var mwt1 by remember { mutableStateOf(formState.mwt1) }
    var mwt2 by remember { mutableStateOf(formState.mwt2) }
    var mwt1Best by remember { mutableStateOf(formState.mwt1Best) }
    var copdLevel by remember { mutableStateOf("") }

    // Use rememberScrollState for managing scrolling state
    val scrollState = rememberScrollState()
    val context = LocalContext.current

    val showDialog = remember { mutableStateOf(false) }

    if (showDialog.value) {
        CopdDialog(copdLevel = copdLevel, onDismissRequest = {
            showDialog.value = false
            onNavigate()
        })
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { padding ->
        // Adjust the padding to shift the content with horizontal padding of 50 dp and vertical padding of 100 dp
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 50.dp, vertical = 100.dp) // Horizontal and Vertical padding
                .padding(padding) // To apply Scaffold's padding
                .verticalScroll(scrollState), // Enable vertical scrolling
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Please fill out the form below:",
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(10.dp)
            )

            // Form Fields (Same as before)
            InputFieldWithInfo("Age", age, "Enter the age of the patient in years.") { age = it }
            InputFieldWithInfo("Pack History", packHistory, "The number of cigarette packs smoked per day multiplied by the number of years.") { packHistory = it }
            InputFieldWithInfo("FEV1", fev1, "Forced Expiratory Volume in 1 second (L).", KeyboardType.Decimal) { fev1 = it }
            InputFieldWithInfo("FVC", fvc, "Forced Vital Capacity (L).", KeyboardType.Decimal) { fvc = it }
            InputFieldWithInfo("FEV1/FVC", fev1Fvc, "Ratio of FEV1 to FVC expressed as a percentage.", KeyboardType.Decimal) { fev1Fvc = it }
            InputFieldWithInfo("CAT", cat, "The COPD Assessment Test score.") { cat = it }
            InputFieldWithInfo("SGRQ", sgrq, "The St. George’s Respiratory Questionnaire score.", KeyboardType.Decimal) { sgrq = it }


            // Gender (Radio buttons)
            RadioButtonGroup(
                label = "Gender",
                options = listOf("Female" to "0", "Male" to "1"),
                selectedValue = gender,
                onValueChange = { gender = it }
            )

            // Diabetes (Radio buttons)
            RadioButtonGroup(
                label = "Diabetes",
                options = listOf("No" to "0", "Yes" to "1"),
                selectedValue = diabetes,
                onValueChange = { diabetes = it }
            )

            // Hypertension (Radio buttons)
            RadioButtonGroup(
                label = "Hypertension",
                options = listOf("No" to "0", "Yes" to "1"),
                selectedValue = hypertension,
                onValueChange = { hypertension = it }
            )

            InputFieldWithInfo("MWT1", mwt1, "The first 6-minute walk test distance in meters.") { mwt1 = it }
            InputFieldWithInfo("MWT2", mwt2, "The second 6-minute walk test distance in meters.") { mwt2 = it }
            InputFieldWithInfo("MWTBest", mwt1Best, "The best distance achieved in the 6-minute walk tests.", KeyboardType.Decimal) { mwt1Best = it }

            // Spacer to push the submit button at the bottom
            Spacer(modifier = Modifier.weight(1f))


            // Submit Button
            Button(
            onClick = {
                // Validate fields
                when {
                    age.isEmpty() -> showToast(context, "Age field is empty.")
                    packHistory.isEmpty() -> showToast(context, "Pack History field is empty.")
                    fev1.isEmpty() -> showToast(context, "FEV1 field is empty.")
                    fvc.isEmpty() -> showToast(context, "FVC field is empty.")
                    fev1Fvc.isEmpty() -> showToast(context, "FEV1/FVC field is empty.")
                    cat.isEmpty() -> showToast(context, "CAT field is empty.")
                    sgrq.isEmpty() -> showToast(context, "SGRQ field is empty.")
                    mwt1.isEmpty() -> showToast(context, "MWT1 field is empty.")
                    mwt2.isEmpty() -> showToast(context, "MWT2 field is empty.")
                    mwt1Best.isEmpty() -> showToast(context, "MWT1Best field is empty.")
                    else -> {
                        // Update formState
                        formState.age = age
                        formState.packHistory = packHistory
                        formState.fev1 = fev1
                        formState.fvc = fvc
                        formState.fev1Fvc = fev1Fvc
                        formState.cat = cat
                        formState.sgrq = sgrq
                        formState.gender = gender
                        formState.diabetes = diabetes
                        formState.hypertension = hypertension
                        formState.mwt1 = mwt1
                        formState.mwt2 = mwt2
                        formState.mwt1Best = mwt1Best


                        val result = predictCOPDSeverity(
                            age = age.toInt(),
                            smoking = smoking.toInt(),
                            packHistory = packHistory.toFloat(),
                            fev1 = fev1.toFloat(),
                            fvc = fvc.toFloat(),
                            cat = cat.toInt(),
                            sgrq = sgrq.toFloat(),
                            gender = gender.toInt(),
                            diabetes = diabetes.toInt(),
                            hypertension = hypertension.toInt(),
                            mwt1 = mwt1.toFloat(),
                            mwt2 = mwt2.toFloat(),
                            mwt1Best = mwt1Best.toFloat(),
                            context = context
                        )

                        copdLevel = result
                        storeSeverity(result, cat, context)
                        showDialog.value = true

                        Log.d("Prediction", "Result: $result")
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 60.dp) // Padding to avoid overlap with BottomBar
            ) {
                Text("Submit")
            }
        }
    }
}

fun showToast(context: android.content.Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}



@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun InputFieldWithInfo(
    label: String,
    value: String,
    description: String,
    keyboardType: KeyboardType = KeyboardType.Number,
    onValueChange: (String) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
            trailingIcon = {
                IconButton(onClick = { showDialog = true }) {
                    Icon(imageVector = Icons.Default.Info, contentDescription = "Info")
                }
            }
        )

        if (showDialog) {
            Dialog(onDismissRequest = { showDialog = false }) {
                Surface(
                    shape = MaterialTheme.shapes.medium,
                    color = Color.White, // Explicit white background
                    modifier = Modifier.padding(16.dp)


                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = "Info",
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = description,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { showDialog = false },
                            modifier = Modifier.align(Alignment.End)
                        ) {
                            Text("Close")
                        }
                    }
                }
            }
        }
    }
}




@Composable
fun RadioButtonGroup(
    label: String,
    options: List<Pair<String, String>>, // List of (label, value)
    selectedValue: String,
    onValueChange: (String) -> Unit
) {
    Column {
        Text(text = label, fontSize = 18.sp, modifier = Modifier.padding(bottom = 8.dp))
        options.forEach { option ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable { onValueChange(option.second) }, // Clickable entire row
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selectedValue == option.second,
                    onClick = { onValueChange(option.second) } // Update selection
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = option.first)
            }
        }
    }
}

@Composable
fun PredictionScreen(onNavigate: () -> Unit = {}) {
    val formState = remember { FormState() }
    InputForm(formState, onNavigate)
}

@Composable
private fun CopdDialog(copdLevel: String, onDismissRequest: () -> Unit){
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "These are the results from our COPD prediction model.",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
                Text(
                    text = "\n Your COPD level is: $copdLevel",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                TextButton(
                    onClick = { onDismissRequest() },
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text("OK")
                }
            }
        }

    }
}


@Composable
private fun HelperDialog(onDismissRequest: () -> Unit){
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(275.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "This questionnaire will help you and your healthcare professional measure the impact COPD (Chronic Obstructive Pulmonary Disease) is having on your wellbeing and daily life. \n This score should only be interpreted and used in cooperation with healthcare personnel.",
                    modifier = Modifier.padding(16.dp),
                    textAlign = TextAlign.Center
                )
                TextButton(
                    onClick = { onDismissRequest() },
                    modifier = Modifier.padding(8.dp),
                ) {
                    Text("OK")
                }
            }
        }

    }
}

