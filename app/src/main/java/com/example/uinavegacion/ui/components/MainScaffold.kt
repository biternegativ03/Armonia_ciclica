package com.armoniaciclica.app.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.armoniaciclica.app.navigation.Route

@Composable
fun MainScaffold(
    navController: NavController,
    title: String,
    showBackButton: Boolean = false,
    showBottomBar: Boolean = true,
    currentRoute: String,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            AppTopBar(
                title = title,
                onNavigateBack = if (showBackButton) { { navController.navigateUp() } } else null,
                showMenu = !showBackButton,
                onHomeClick = { navController.navigate(Route.Home.path) },
                onCalendarClick = { navController.navigate(Route.Calendar.path) },
                onSymptomsClick = { navController.navigate(Route.Symptoms.path) },
                onEducationClick = { navController.navigate(Route.Education.path) },
                onProfileClick = { navController.navigate(Route.Profile.path) },
                currentRoute = currentRoute
            )
        },
        content = { paddingValues ->
            content(paddingValues)
        }
    )
}