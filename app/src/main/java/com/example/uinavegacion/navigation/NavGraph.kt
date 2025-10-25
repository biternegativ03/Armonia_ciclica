package com.armoniaciclica.app.navigation
import androidx.compose.foundation.layout.padding // Para aplicar innerPadding
import androidx.compose.material3.Scaffold // Estructura base con slots
import androidx.compose.runtime.Composable // Marcador composable
import androidx.compose.ui.Modifier // Modificador
import androidx.navigation.NavHostController // Controlador de navegación
import androidx.navigation.compose.NavHost // Contenedor de destinos
import androidx.navigation.compose.composable // Declarar cada destino
import kotlinx.coroutines.launch // Para abrir/cerrar drawer con corrutinas

import androidx.compose.material3.ModalNavigationDrawer // Drawer lateral modal
import androidx.compose.material3.rememberDrawerState // Estado del drawer
import androidx.compose.material3.DrawerValue // Valores (Opened/Closed)
import androidx.compose.runtime.rememberCoroutineScope // Alcance de corrutina

// Importar todas las pantallas
import com.armoniaciclica.app.ui.screen.*

@Composable // Gráfico de navegación completo
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Route.Welcome.path
    ) {
        // Onboarding screens
        composable(Route.Welcome.path) {
            WelcomeScreen(
                onStartClick = { navController.navigate(Route.ConfigWelcome.path) },
                onLoginClick = { navController.navigate(Route.Login.path) }
            )
        }
        
        composable(Route.Login.path) {
            LoginScreen(
                onLoginClick = { navController.navigate(Route.Home.path) },
                onRegisterClick = { navController.navigate(Route.Register.path) },
                onRecoverPasswordClick = { navController.navigate(Route.RecoverPassword.path) }
            )
        }
        
        composable(Route.Register.path) {
            RegisterScreen(
                onRegisterClick = { navController.navigate(Route.Login.path) },
                onLoginClick = { navController.navigate(Route.Login.path) }
            )
        }
        
        composable(Route.RecoverPassword.path) {
            RecoverPasswordScreen(
                onSendLinkClick = { navController.navigate(Route.Login.path) },
                onBackToLoginClick = { navController.navigate(Route.Login.path) }
            )
        }
        
        // Configuration screens
        composable(Route.ConfigWelcome.path) {
            ConfigWelcomeScreen(
                onContinueClick = { navController.navigate(Route.ProfileSelection.path) }
            )
        }
        
        composable(Route.ProfileSelection.path) {
            ProfileSelectionScreen(
                onContinueClick = { navController.navigate(Route.BasicData.path) }
            )
        }
        
        composable(Route.BasicData.path) {
            BasicDataScreen(
                onContinueClick = { navController.navigate(Route.LastPeriod.path) }
            )
        }
        
        composable(Route.LastPeriod.path) {
            LastPeriodScreen(
                onConfirmDateClick = { navController.navigate(Route.DataConfirmation.path) }
            )
        }
        
        composable(Route.DataConfirmation.path) {
            DataConfirmationScreen(
                onEditClick = { navController.navigate(Route.ProfileSelection.path) },
                onStartUsingClick = { navController.navigate(Route.Home.path) }
            )
        }
        
        // Main app screens
        composable(Route.Home.path) {
            HomeScreen(
                onRegisterSymptomsClick = { navController.navigate(Route.Symptoms.path) },
                onCalendarClick = { navController.navigate(Route.Calendar.path) },
                onSymptomsClick = { navController.navigate(Route.Symptoms.path) },
                onEducationClick = { navController.navigate(Route.Education.path) },
                onProfileClick = { navController.navigate(Route.Profile.path) }
            )
        }
        
        composable(Route.Calendar.path) {
            CalendarScreen(
                onHomeClick = { navController.navigate(Route.Home.path) },
                onSymptomsClick = { navController.navigate(Route.Symptoms.path) },
                onEducationClick = { navController.navigate(Route.Education.path) },
                onProfileClick = { navController.navigate(Route.Profile.path) }
            )
        }
        
        composable(Route.Symptoms.path) {
            SymptomsScreen(
                onSaveSymptomsClick = { navController.navigate(Route.SymptomConfirmation.path) },
                onHomeClick = { navController.navigate(Route.Home.path) },
                onCalendarClick = { navController.navigate(Route.Calendar.path) },
                onEducationClick = { navController.navigate(Route.Education.path) },
                onProfileClick = { navController.navigate(Route.Profile.path) }
            )
        }
        
        composable(Route.Education.path) {
            EducationScreen(
                onArticleClick = { navController.navigate(Route.ArticleDetail.path) },
                onHomeClick = { navController.navigate(Route.Home.path) },
                onCalendarClick = { navController.navigate(Route.Calendar.path) },
                onSymptomsClick = { navController.navigate(Route.Symptoms.path) },
                onProfileClick = { navController.navigate(Route.Profile.path) }
            )
        }
        
        composable(Route.Profile.path) {
            ProfileScreen(
                onEditProfileClick = { navController.navigate(Route.EditProfile.path) },
                onNotificationsClick = { navController.navigate(Route.NotificationSettings.path) },
                onGoalsClick = { navController.navigate(Route.GoalSettings.path) },
                onStatisticsClick = { /* TODO: Implementar estadísticas */ },
                onHomeClick = { navController.navigate(Route.Home.path) },
                onCalendarClick = { navController.navigate(Route.Calendar.path) },
                onSymptomsClick = { navController.navigate(Route.Symptoms.path) },
                onEducationClick = { navController.navigate(Route.Education.path) }
            )
        }
        
        // Profile management screens
        composable(Route.EditProfile.path) {
            EditProfileScreen(
                onSaveClick = { navController.navigate(Route.Profile.path) }
            )
        }
        
        composable(Route.NotificationSettings.path) {
            NotificationSettingsScreen(
                onBackClick = { navController.navigate(Route.Profile.path) }
            )
        }
        
        composable(Route.GoalSettings.path) {
            GoalSettingsScreen(
                onBackClick = { navController.navigate(Route.Profile.path) }
            )
        }
        
        // Symptom screens
        composable(Route.SymptomConfirmation.path) {
            SymptomConfirmationScreen(
                onBackClick = { navController.navigate(Route.Home.path) }
            )
        }
        
        // Education screens
        composable(Route.ArticleDetail.path) {
            ArticleDetailScreen(
                onBackClick = { navController.navigate(Route.Education.path) }
            )
        }
    }
}