package com.example.copd_asthma.screens

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


data class FormState(
    var age: String = "",
    var packHistory: String = "",
    var fev1: String = "",
    var fvc: String = "",
    var fev1Fvc: String = "",
    var cat: String = "",
    var sgrq: String = "",
    var gender: String = "0", // Default to Female
    var diabetes: String = "0", // Default to No
    var hypertension: String = "0", // Default to No
    var mwt1: String = "",
    var mwt2: String = "",
    var mwt1Best: String = ""
)

@Composable
fun InputForm(formState: FormState, onNavigate: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Please fill out the form below:",
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(10.dp)
        )
        InputField("Age", formState.age) { formState.age = it }
        InputField("Pack History", formState.packHistory) { formState.packHistory = it }
        InputField("FEV1", formState.fev1, KeyboardType.Decimal) { formState.fev1 = it }
        InputField("FVC", formState.fvc, KeyboardType.Decimal) { formState.fvc = it }
        InputField("FEV1/FVC", formState.fev1Fvc, KeyboardType.Decimal) { formState.fev1Fvc = it }
        InputField("CAT", formState.cat) { formState.cat = it }
        InputField("SGRQ", formState.sgrq, KeyboardType.Decimal) { formState.sgrq = it }
        RadioButtonGroup("Gender", listOf("Female" to "0", "Male" to "1"), formState.gender) { formState.gender = it }
        RadioButtonGroup("Diabetes", listOf("No" to "0", "Yes" to "1"), formState.diabetes) { formState.diabetes = it }
        RadioButtonGroup("Hypertension", listOf("No" to "0", "Yes" to "1"), formState.hypertension) { formState.hypertension = it }
        InputField("MWT1", formState.mwt1) { formState.mwt1 = it }
        InputField("MWT2", formState.mwt2) { formState.mwt2 = it }
        InputField("MWT1Best", formState.mwt1Best, KeyboardType.Decimal) { formState.mwt1Best = it }

        Button(
            onClick = {

                onNavigate()
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Submit")
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    label: String,
    value: String,
    keyboardType: KeyboardType = KeyboardType.Number,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType)
    )
}
@Composable
fun RadioButtonGroup(
    label: String,
    options: List<Pair<String, String>>,
    selectedValue: String,
    onValueChange: (String) -> Unit
) {
    Column {
        Text(label)
        options.forEach { option ->
            Row(modifier = Modifier.fillMaxWidth()) {
                RadioButton(
                    selected = selectedValue == option.second,
                    onClick = { onValueChange(option.second) },
                    interactionSource = remember { MutableInteractionSource() } // Avoid ambiguity
                )
                Text(option.first)
            }
        }
    }
}


@Composable
fun PredictionScreen(onNavigate: () -> Unit = {}) {
    val formState = remember { FormState() }
    InputForm(formState, onNavigate)
}