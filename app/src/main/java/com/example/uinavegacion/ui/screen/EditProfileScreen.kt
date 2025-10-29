package com.armoniaciclica.app.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.armoniaciclica.app.ui.theme.*
import com.example.uinavegacion.domain.validation.*

@Composable
fun EditProfileScreen(
    onSaveClick: () -> Unit
) {
    var fullName by remember { mutableStateOf("María González") }
    var email by remember { mutableStateOf("maria@example.com") }
    var phone by remember { mutableStateOf("+1234567890") }
    var age by remember { mutableStateOf("25") }

    // Estados de error
    var fullNameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var phoneError by remember { mutableStateOf<String?>(null) }
    var ageError by remember { mutableStateOf<String?>(null) }
    
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
                    text = "Editar perfil",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.height(40.dp))
            
            // Foto de perfil
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(PinkLight),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile Picture",
                    tint = PinkPrimary,
                    modifier = Modifier.size(48.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Botón de cambiar foto
            Button(
                onClick = { /* Cambiar foto */ },
                colors = ButtonDefaults.buttonColors(
                    containerColor = PinkLight
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = "Cambiar foto de perfil",
                    color = PinkPrimary,
                    fontSize = 14.sp
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
                    value = fullName,
                    onValueChange = {
                        fullName = it
                        fullNameError = validateNameLettersOnly(it)
                    },
                    label = { Text("Nombre completo") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PinkPrimary,
                        focusedLabelColor = PinkPrimary,
                        errorBorderColor = Color.Red,
                        errorLabelColor = Color.Red
                    ),
                    shape = RoundedCornerShape(12.dp),
                    isError = fullNameError != null
                )

                AnimatedVisibility(visible = fullNameError != null, enter = fadeIn(), exit = fadeOut()) {
                    if (fullNameError != null) Text(text = fullNameError!!, color = Color.Red, style = MaterialTheme.typography.bodySmall)
                }
                
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        emailError = validateEmail(it)
                    },
                    label = { Text("Correo electrónico") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PinkPrimary,
                        focusedLabelColor = PinkPrimary,
                        errorBorderColor = Color.Red,
                        errorLabelColor = Color.Red
                    ),
                    shape = RoundedCornerShape(12.dp),
                    isError = emailError != null
                )

                AnimatedVisibility(visible = emailError != null, enter = fadeIn(), exit = fadeOut()) {
                    if (emailError != null) Text(text = emailError!!, color = Color.Red, style = MaterialTheme.typography.bodySmall)
                }
                
                OutlinedTextField(
                    value = phone,
                    onValueChange = {
                        phone = it
                        phoneError = validatePhoneDigitsOnly(it)
                    },
                    label = { Text("Teléfono") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PinkPrimary,
                        focusedLabelColor = PinkPrimary,
                        errorBorderColor = Color.Red,
                        errorLabelColor = Color.Red
                    ),
                    shape = RoundedCornerShape(12.dp),
                    isError = phoneError != null
                )

                AnimatedVisibility(visible = phoneError != null, enter = fadeIn(), exit = fadeOut()) {
                    if (phoneError != null) Text(text = phoneError!!, color = Color.Red, style = MaterialTheme.typography.bodySmall)
                }
                
                OutlinedTextField(
                    value = age,
                    onValueChange = {
                        age = it
                        ageError = validateAge(it)
                    },
                    label = { Text("Edad") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PinkPrimary,
                        focusedLabelColor = PinkPrimary,
                        errorBorderColor = Color.Red,
                        errorLabelColor = Color.Red
                    ),
                    shape = RoundedCornerShape(12.dp),
                    isError = ageError != null
                )

                AnimatedVisibility(visible = ageError != null, enter = fadeIn(), exit = fadeOut()) {
                    if (ageError != null) Text(text = ageError!!, color = Color.Red, style = MaterialTheme.typography.bodySmall)
                }
            }
            
            Spacer(modifier = Modifier.height(40.dp))
            
            val allValid = listOf(fullNameError, emailError, phoneError, ageError).all { it == null } &&
                    fullName.isNotBlank() && email.isNotBlank() && phone.isNotBlank() && age.isNotBlank()

            // Botón de guardar
            Button(
                onClick = {
                    if (allValid) onSaveClick()
                },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp),
                enabled = allValid,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (allValid) PinkPrimary else Color.LightGray
                ),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text(
                    text = "Guardar cambios",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}
