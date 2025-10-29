@file:Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
package com.armoniaciclica.app.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import android.app.DatePickerDialog
import androidx.compose.ui.platform.LocalContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.armoniaciclica.app.ui.theme.PinkPrimary
import com.armoniaciclica.app.ui.theme.PurplePrimary
import com.example.uinavegacion.viewmodel.SharedViewModel

@Composable
fun LastPeriodScreen(
    sharedViewModel: SharedViewModel,
    onConfirmDateClick: () -> Unit
) {
    val context = LocalContext.current
    val today = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.getDefault())

    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }

    // DatePickerDialog
    val calendar = Calendar.getInstance()
    val dpd = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )
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
                    text = "Último período",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            
            Spacer(modifier = Modifier.height(40.dp))
            
            // Icono de calendario
            Icon(
                imageVector = Icons.Default.CalendarToday,
                contentDescription = null,
                tint = PinkPrimary,
                modifier = Modifier.size(48.dp)
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Pregunta principal
            Text(
                text = "¿Cuándo comenzó tu último período?",
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
            
            Spacer(modifier = Modifier.height(40.dp))
            
            // Selector de fecha - muestra la fecha seleccionada o el placeholder
            Card(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
                    .height(200.dp)
                    .clickable { dpd.show() },
                colors = CardDefaults.cardColors(
                    containerColor = Color.LightGray.copy(alpha = 0.3f)
                ),
                shape = RoundedCornerShape(12.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    if (selectedDate != null) {
                        Text(
                            text = selectedDate!!.format(formatter),
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium
                        )
                    } else {
                        Text(
                            text = "Calendario de selección",
                            color = Color.Gray,
                            fontSize = 16.sp
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Texto explicativo
            Text(
                text = "Esta información nos ayuda a predecir tu próximo ciclo",
                fontSize = 14.sp,
                textAlign = TextAlign.Center,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
            
            Spacer(modifier = Modifier.height(40.dp))
            
                    // Botón de confirmar fecha (deshabilitado si no hay fecha seleccionada)
                    val canConfirm = selectedDate != null

                    Button(
                        onClick = {
                            if (canConfirm) {
                                // Guardar la fecha seleccionada en el ViewModel compartido
                                sharedViewModel.lastPeriodDate = selectedDate
                                onConfirmDateClick()
                            }
                        },
                        enabled = canConfirm,
                        modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(50.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (canConfirm) PinkPrimary else Color.LightGray
                        ),
                        shape = RoundedCornerShape(25.dp)
                    ) {
                        Text(
                            text = "Confirmar fecha",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
        }
    }
}
