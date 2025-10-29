package com.armoniaciclica.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.armoniaciclica.app.navigation.AppNavGraph
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.armoniaciclica.app.ui.components.AppTopBar
import com.armoniaciclica.app.ui.theme.ArmoniaciclicaTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppRoot()
        }
    }
}


/*
* En Compose, Surface es un contenedor visual que viene de Material 3.Crea un bloque
*  que puedes personalizar con color, forma, sombra (elevación).
Sirve para aplicar un fondo (color, borde, elevación, forma) siguiendo las guías de diseño
* de Material.
Piensa en él como una “lona base” sobre la cual vas a pintar tu UI.
* Si cambias el tema a dark mode, colorScheme.background
* cambia automáticamente y el Surface pinta la pantalla con el nuevo color.
* */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppRoot() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    ArmoniaciclicaTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            Scaffold(
                contentWindowInsets = WindowInsets.safeDrawing,
                topBar = {
                    // Solo mostrar TopBar en pantallas principales post-onboarding
                    when (currentRoute) {
                        "home", "calendar", "symptoms", "education", "profile" -> {
                            AppTopBar(
                                title = when (currentRoute) {
                                    "home" -> "Inicio"
                                    "calendar" -> "Calendario"
                                    "symptoms" -> "Síntomas"
                                    "education" -> "Educación"
                                    "profile" -> "Perfil"
                                    else -> ""
                                },
                                onNavigateBack = if (navController.previousBackStackEntry != null) {
                                    { navController.navigateUp() }
                                } else null,
                                showMenu = true,
                                onHomeClick = { navController.navigate("home") },
                                onCalendarClick = { navController.navigate("calendar") },
                                onSymptomsClick = { navController.navigate("symptoms") },
                                onEducationClick = { navController.navigate("education") },
                                onProfileClick = { navController.navigate("profile") },
                                currentRoute = currentRoute ?: ""
                            )
                        }
                    }
                }
            ) { innerPadding ->
                AppNavGraph(
                    navController = navController,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}