@file:Suppress("UNUSED_PARAMETER", "UNUSED_VARIABLE")
package com.armoniaciclica.app.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.armoniaciclica.app.ui.theme.*

@Composable
fun SymptomsScreen(
    onSaveSymptomsClick: () -> Unit,
    onHomeClick: () -> Unit,
    onCalendarClick: () -> Unit,
    onEducationClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    val symptoms = listOf(
        "Cólicos" to Icons.Default.LocalHospital,
        "Hinchazón" to Icons.Default.Water,
        "Dolor" to Icons.Default.Warning,
        "Humor" to Icons.Default.Mood,
        "Fatiga" to Icons.Default.BatteryAlert,
        "Flujo" to Icons.Default.WaterDrop
    )
    
    var selectedSymptoms by remember { mutableStateOf(setOf<String>()) }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
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
                    text = "Síntomas",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            
            // Contenido principal
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Pregunta principal
                Text(
                    text = "¿Cómo te sientes hoy?",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                
                // Grid de síntomas
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.height(300.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(symptoms) { (symptom, icon) ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(80.dp)
                                .clickable {
                                    selectedSymptoms = if (symptom in selectedSymptoms) {
                                        selectedSymptoms - symptom
                                    } else {
                                        selectedSymptoms + symptom
                                    }
                                },
                            colors = CardDefaults.cardColors(
                                containerColor = if (symptom in selectedSymptoms) SymptomSelected else SymptomUnselected
                            ),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = symptom,
                                    tint = if (symptom in selectedSymptoms) Color.White else Color.Gray,
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = symptom,
                                    color = if (symptom in selectedSymptoms) Color.White else Color.Gray,
                                    fontSize = 14.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                        }
                    }
                }
                
                // Botón de guardar síntomas
                Button(
                    onClick = onSaveSymptomsClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PinkPrimary
                    ),
                    shape = RoundedCornerShape(25.dp)
                ) {
                    Text(
                        text = "Guardar síntomas",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
        
        // Bottom Navigation removed: AppTopBar in AppRoot provides the top navigation
    }
}
