package com.armoniaciclica.app.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.armoniaciclica.app.ui.theme.PinkPrimary
import com.armoniaciclica.app.ui.theme.PurplePrimary
import com.example.uinavegacion.domain.validation.*
import com.example.uinavegacion.viewmodel.SharedViewModel

@Composable
fun BasicDataScreen(
    sharedViewModel: SharedViewModel,
    onContinueClick: () -> Unit
) {
    var age by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var contraceptiveMethod by remember { mutableStateOf("") }

    // Estados de error
    var ageError by remember { mutableStateOf<String?>(null) }
    var weightError by remember { mutableStateOf<String?>(null) }
    var heightError by remember { mutableStateOf<String?>(null) }
    var methodError by remember { mutableStateOf<String?>(null) }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header con gradiente
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(PinkPrimary, PurplePrimary)
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Datos básicos",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.height(40.dp))
            
            // Campos de entrada
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = age,
                    onValueChange = {
                        age = it
                        ageError = validateAge(it)
                    },
                    label = { Text("Edad (años)") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PinkPrimary,
                        focusedLabelColor = PinkPrimary,
                        errorBorderColor = Color.Red,
                        errorLabelColor = Color.Red
                    ),
                    shape = RoundedCornerShape(12.dp),
                    isError = ageError != null,
                    supportingText = {
                        if (ageError != null) Text(text = ageError!!, color = Color.Red, style = MaterialTheme.typography.bodySmall)
                    }
                )
                
                OutlinedTextField(
                    value = weight,
                    onValueChange = {
                        weight = it
                        weightError = validateWeight(it)
                    },
                    label = { Text("Peso (kg)") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PinkPrimary,
                        focusedLabelColor = PinkPrimary,
                        errorBorderColor = Color.Red,
                        errorLabelColor = Color.Red
                    ),
                    shape = RoundedCornerShape(12.dp),
                    isError = weightError != null,
                    supportingText = {
                        if (weightError != null) Text(text = weightError!!, color = Color.Red, style = MaterialTheme.typography.bodySmall)
                    }
                )
                
                OutlinedTextField(
                    value = height,
                    onValueChange = {
                        height = it
                        heightError = validateHeight(it)
                    },
                    label = { Text("Altura (cm)") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PinkPrimary,
                        focusedLabelColor = PinkPrimary,
                        errorBorderColor = Color.Red,
                        errorLabelColor = Color.Red
                    ),
                    shape = RoundedCornerShape(12.dp),
                    isError = heightError != null,
                    supportingText = {
                        if (heightError != null) Text(text = heightError!!, color = Color.Red, style = MaterialTheme.typography.bodySmall)
                    }
                )
                
                OutlinedTextField(
                    value = contraceptiveMethod,
                    onValueChange = {
                        contraceptiveMethod = it
                        methodError = validateContraceptive(it)
                    },
                    label = { Text("Método anticonceptivo") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PinkPrimary,
                        focusedLabelColor = PinkPrimary,
                        errorBorderColor = Color.Red,
                        errorLabelColor = Color.Red
                    ),
                    shape = RoundedCornerShape(12.dp),
                    isError = methodError != null,
                    supportingText = {
                        if (methodError != null) Text(text = methodError!!, color = Color.Red, style = MaterialTheme.typography.bodySmall)
                    }
                )
            }
            
            Spacer(modifier = Modifier.height(60.dp))
            
            // Botón de continuar (habilitado sólo si todos los campos están válidos)
            val allValid = listOf(ageError, weightError, heightError, methodError).all { it == null } &&
                    age.isNotBlank() && weight.isNotBlank() && height.isNotBlank() && contraceptiveMethod.isNotBlank()

            Button(
                onClick = {
                    if (allValid) {
                        // Guardar en el ViewModel compartido antes de continuar
                        sharedViewModel.age = age.trim()
                        sharedViewModel.weight = weight.trim()
                        sharedViewModel.height = height.trim()
                        sharedViewModel.contraceptiveMethod = contraceptiveMethod.trim()
                        onContinueClick()
                    }
                },
                enabled = allValid,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (allValid) PinkPrimary else Color.LightGray
                ),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text(
                    text = "Continuar",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
