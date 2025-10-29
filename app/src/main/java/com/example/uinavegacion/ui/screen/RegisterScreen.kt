package com.armoniaciclica.app.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.armoniaciclica.app.ui.theme.PinkPrimary
import com.armoniaciclica.app.ui.theme.PurplePrimary
import com.example.uinavegacion.domain.validation.*

@Composable
fun RegisterScreen(
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // Estados de error usando los validadores centrales
    var fullNameError by remember { mutableStateOf<String?>(null) }
    var emailError by remember { mutableStateOf<String?>(null) }
    var phoneError by remember { mutableStateOf<String?>(null) }
    var passwordError by remember { mutableStateOf<String?>(null) }
    
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
                    text = "Registro",
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

                AnimatedVisibility(
                    visible = fullNameError != null,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
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

                AnimatedVisibility(
                    visible = emailError != null,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
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

                AnimatedVisibility(
                    visible = phoneError != null,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    if (phoneError != null) Text(text = phoneError!!, color = Color.Red, style = MaterialTheme.typography.bodySmall)
                }
                
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        passwordError = validateStrongPass(it)
                    },
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PinkPrimary,
                        focusedLabelColor = PinkPrimary,
                        errorBorderColor = Color.Red,
                        errorLabelColor = Color.Red
                    ),
                    shape = RoundedCornerShape(12.dp),
                    isError = passwordError != null
                )

                AnimatedVisibility(
                    visible = passwordError != null,
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    if (passwordError != null) Text(text = passwordError!!, color = Color.Red, style = MaterialTheme.typography.bodySmall)
                }
            }
            
            Spacer(modifier = Modifier.height(40.dp))
            
            // Botón de crear cuenta
            val allValid = listOf(fullNameError, emailError, phoneError, passwordError).all { it == null } &&
                    fullName.isNotBlank() && email.isNotBlank() && phone.isNotBlank() && password.isNotBlank()

            Button(
                onClick = {
                    if (allValid) onRegisterClick()
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
                    text = "Crear cuenta",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Enlace de login
            TextButton(
                onClick = onLoginClick,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Ya tienes cuenta? Inicia sesión",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
            }
        }
    }
}