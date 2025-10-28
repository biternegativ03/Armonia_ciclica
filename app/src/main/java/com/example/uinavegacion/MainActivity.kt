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
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.armoniaciclica.app.navigation.AppNavGraph
import com.armoniaciclica.app.ui.components.AppTopBar
import com.armoniaciclica.app.ui.theme.ArmoniaciclicaTheme

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
@Composable // Indica que esta función dibuja UI
fun AppRoot() { // Raíz de la app para separar responsabilidades
    val navController = rememberNavController() // Controlador de navegación
    ArmoniaciclicaTheme { // Provee colores/tipografías personalizados
        Surface(color = MaterialTheme.colorScheme.background) { // Fondo general
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route ?: ""

            Scaffold(
                topBar = {
                    AppTopBar(
                        title = "",
                        onNavigateBack = null,
                        showMenu = true,
                        onHomeClick = { navController.navigate(com.armoniaciclica.app.navigation.Route.Home.path) },
                        onCalendarClick = { navController.navigate(com.armoniaciclica.app.navigation.Route.Calendar.path) },
                        onSymptomsClick = { navController.navigate(com.armoniaciclica.app.navigation.Route.Symptoms.path) },
                        onEducationClick = { navController.navigate(com.armoniaciclica.app.navigation.Route.Education.path) },
                        onProfileClick = { navController.navigate(com.armoniaciclica.app.navigation.Route.Profile.path) },
                        currentRoute = currentRoute
                    )
                }
            ) { innerPadding ->
                androidx.compose.foundation.layout.Box(modifier = androidx.compose.ui.Modifier.padding(innerPadding)) {
                    AppNavGraph(navController = navController)
                }
            }
        }
    }
}