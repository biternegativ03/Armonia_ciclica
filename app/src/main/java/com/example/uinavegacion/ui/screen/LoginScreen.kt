package com.armoniaciclica.app.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.uinavegacion.viewmodel.AuthViewModel
import com.armoniaciclica.app.ui.theme.PinkPrimary
import com.armoniaciclica.app.ui.theme.PurplePrimary

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onRecoverPasswordClick: () -> Unit,
    vm: AuthViewModel = viewModel()
) {
    val ui by vm.login.collectAsState()

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
                    text = "Iniciar sesión",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(Modifier.height(40.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                OutlinedTextField(
                    value = ui.email,
                    onValueChange = vm::onEmailChange,
                    label = { Text("Correo electrónico") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    isError = ui.emailError != null,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PinkPrimary,
                        focusedLabelColor = PinkPrimary,
                        errorBorderColor = Color.Red,
                        errorLabelColor = Color.Red
                    )
                )
                AnimatedVisibility(visible = ui.emailError != null) {
                    Text(ui.emailError ?: "", color = Color.Red, fontSize = 12.sp)
                }

                OutlinedTextField(
                    value = ui.password,
                    onValueChange = vm::onPasswordChange,
                    label = { Text("Contraseña") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    isError = ui.passwordError != null,
                    shape = RoundedCornerShape(12.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PinkPrimary,
                        focusedLabelColor = PinkPrimary,
                        errorBorderColor = Color.Red,
                        errorLabelColor = Color.Red
                    )
                )
                AnimatedVisibility(visible = ui.passwordError != null) {
                    Text(ui.passwordError ?: "", color = Color.Red, fontSize = 12.sp)
                }
            }

            TextButton(onClick = onRecoverPasswordClick) {
                Text("¿Olvidaste tu contraseña?", color = PinkPrimary, fontSize = 14.sp)
            }

            Spacer(Modifier.height(16.dp))

            Button(
                onClick = { vm.submitLogin(onSuccess = onLoginClick, onInvalid = {}) },
                enabled = ui.valid && !ui.submitting,
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PinkPrimary),
                shape = RoundedCornerShape(25.dp)
            ) {
                Text("Iniciar sesión", color = Color.White, fontSize = 16.sp, fontWeight = FontWeight.Medium)
            }

            Spacer(Modifier.height(24.dp))

            TextButton(onClick = onRegisterClick) {
                Text("¿No tienes cuenta? Regístrate", color = Color.Gray, fontSize = 14.sp)
            }
        }
    }
}
