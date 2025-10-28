package com.armoniaciclica.app.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.CenterAlignedTopAppBar // TopAppBar centrada
import androidx.compose.material3.DropdownMenu // Menú desplegable
import androidx.compose.material3.DropdownMenuItem // Opción del menú
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon // Para mostrar íconos
import androidx.compose.material3.IconButton // Botones con ícono
import androidx.compose.material3.MaterialTheme // Tema Material
import androidx.compose.material3.Text // Texto
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.* // remember / mutableStateOf
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.statusBarsPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: String,
    onNavigateBack: (() -> Unit)? = null,
    showMenu: Boolean = true,
    onHomeClick: () -> Unit,
    onCalendarClick: () -> Unit,
    onSymptomsClick: () -> Unit,
    onEducationClick: () -> Unit,
    onProfileClick: () -> Unit,
    currentRoute: String
) {
    var showDropdownMenu by remember { mutableStateOf(false) }

    CenterAlignedTopAppBar(
        modifier = Modifier.statusBarsPadding(),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        navigationIcon = {
            if (onNavigateBack != null) {
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Regresar",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        },
        actions = {
            if (showMenu) {
                IconButton(
                    onClick = onHomeClick,
                    enabled = currentRoute != "home"
                ) {
                    Icon(
                        imageVector = if (currentRoute == "home") Icons.Filled.Home else Icons.Outlined.Home,
                        contentDescription = "Inicio",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                IconButton(
                    onClick = onCalendarClick,
                    enabled = currentRoute != "calendar"
                ) {
                    Icon(
                        imageVector = if (currentRoute == "calendar") Icons.Filled.CalendarMonth else Icons.Outlined.CalendarMonth,
                        contentDescription = "Calendario",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                IconButton(
                    onClick = onSymptomsClick,
                    enabled = currentRoute != "symptoms"
                ) {
                    Icon(
                        imageVector = if (currentRoute == "symptoms") Icons.Filled.HealthAndSafety else Icons.Outlined.HealthAndSafety,
                        contentDescription = "Síntomas",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                IconButton(
                    onClick = onEducationClick,
                    enabled = currentRoute != "education"
                ) {
                    Icon(
                        imageVector = if (currentRoute == "education") Icons.Filled.School else Icons.Outlined.School,
                        contentDescription = "Educación",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
                IconButton(
                    onClick = onProfileClick,
                    enabled = currentRoute != "profile"
                ) {
                    Icon(
                        imageVector = if (currentRoute == "profile") Icons.Filled.Person else Icons.Outlined.Person,
                        contentDescription = "Perfil",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    )
}