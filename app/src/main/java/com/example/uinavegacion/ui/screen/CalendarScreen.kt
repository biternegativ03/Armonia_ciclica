package com.armoniaciclica.app.ui.screen

import android.Manifest
import android.content.ContentUris
import android.content.pm.PackageManager
import android.provider.CalendarContract
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.core.content.ContextCompat
import android.content.Context
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.armoniaciclica.app.ui.components.MainScaffold
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.armoniaciclica.app.ui.theme.*

@Composable
fun CalendarScreen(
    navController: NavController,
    onHomeClick: () -> Unit,
    onSymptomsClick: () -> Unit,
    onEducationClick: () -> Unit,
    onProfileClick: () -> Unit
) {
    MainScaffold(
        navController = navController,
        title = "Calendario",
        currentRoute = "calendar"
    ) { paddingValues -> 
    val context = LocalContext.current
    val calendarDays = (1..31).toList()
    val currentDay = 16
    
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        val readGranted = permissions[Manifest.permission.READ_CALENDAR] ?: false
        val writeGranted = permissions[Manifest.permission.WRITE_CALENDAR] ?: false
        
        if (readGranted && writeGranted) {
            // Aquí cargaremos los eventos del calendario
            loadCalendarEvents(context)
        } else {
            Toast.makeText(
                context,
                "Se necesitan permisos para acceder al calendario",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
    
    // Verificar y solicitar permisos
    fun checkAndRequestPermissions() {
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_CALENDAR
            ) == PackageManager.PERMISSION_GRANTED &&
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.WRITE_CALENDAR
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Ya tenemos los permisos, cargar eventos
            loadCalendarEvents(context)
        } else {
            // Solicitar permisos
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.READ_CALENDAR,
                    Manifest.permission.WRITE_CALENDAR
                )
            )
        }
    }
    
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
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
                    text = "Calendario",
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
                // Mes actual
                Text(
                    text = "Octubre 2024",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                
                // Calendario
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(7),
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(calendarDays) { day ->
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(
                                        if (day == currentDay) FollicularGreen 
                                        else Color.Transparent
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = day.toString(),
                                    color = if (day == currentDay) Color.White else Color.Black,
                                    fontSize = 16.sp,
                                    fontWeight = if (day == currentDay) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        }
                    }
                }
                
                // Leyenda
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(12.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Text(
                            text = "Fases del ciclo:",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                        
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(12.dp)
                                    .clip(CircleShape)
                                    .background(MenstruationRed)
                            )
                            Text(
                                text = "Menstruación",
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                        }
                        
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(12.dp)
                                    .clip(CircleShape)
                                    .background(FollicularGreen)
                            )
                            Text(
                                text = "Folicular",
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                        }
                        
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(12.dp)
                                    .clip(CircleShape)
                                    .background(OvulationBlue)
                            )
                            Text(
                                text = "Ovulación",
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                        }
                        
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(12.dp)
                                    .clip(CircleShape)
                                    .background(LutealPurple)
                            )
                            Text(
                                text = "Lútea",
                                fontSize = 14.sp,
                                color = Color.Black
                            )
                        }
                    }
                }

                // Botón para cargar eventos del calendario
                Button(
                    onClick = { checkAndRequestPermissions() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = PinkPrimary
                    ),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Event,
                            contentDescription = "Calendario",
                            tint = Color.White
                        )
                        Text(
                            text = "Ver eventos del calendario",
                            color = Color.White
                        )
                    }
                }
            }
        }
        
        }
    }
}

private fun loadCalendarEvents(context: Context) {
    val contentResolver = context.contentResolver
    val uri = CalendarContract.Events.CONTENT_URI
    val selection = "(${CalendarContract.Events.DTSTART} >= ?)"
    val now = System.currentTimeMillis()
    val selectionArgs = arrayOf(now.toString())
    
    try {
        val cursor = contentResolver.query(
            uri,
            arrayOf(
                CalendarContract.Events._ID,
                CalendarContract.Events.TITLE,
                CalendarContract.Events.DTSTART,
                CalendarContract.Events.DTEND
            ),
            selection,
            selectionArgs,
            null
        )
        
        cursor?.use { 
            while (it.moveToNext()) {
                val id = it.getLong(0)
                val title = it.getString(1)
                val start = it.getLong(2)
                val end = it.getLong(3)
                
                // Aquí puedes procesar cada evento
                // Por ahora solo mostraremos un Toast como ejemplo
                Toast.makeText(
                    context,
                    "Evento encontrado: $title",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    } catch (e: SecurityException) {
        Toast.makeText(
            context,
            "Error al acceder al calendario: ${e.message}",
            Toast.LENGTH_SHORT
        ).show()
    }
}
