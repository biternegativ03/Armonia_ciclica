package com.armoniaciclica.app.navigation

// Clase sellada para rutas: evita "strings mágicos" y facilita refactors
sealed class Route(val path: String) { // Cada objeto representa una pantalla
    
    // Onboarding screens
    data object Welcome : Route("welcome")
    data object Login : Route("login")
    data object Register : Route("register")
    data object RecoverPassword : Route("recover_password")
    
    // Configuration screens
    data object ConfigWelcome : Route("config_welcome")
    data object ProfileSelection : Route("profile_selection")
    data object BasicData : Route("basic_data")
    data object LastPeriod : Route("last_period")
    data object DataConfirmation : Route("data_confirmation")
    
    // Main app screens
    data object Home : Route("home")
    data object Calendar : Route("calendar")
    data object Symptoms : Route("symptoms")
    data object Education : Route("education")
    data object Profile : Route("profile")
    
    // Profile management screens
    data object EditProfile : Route("edit_profile")
    data object NotificationSettings : Route("notification_settings")
    data object GoalSettings : Route("goal_settings")
    
    // Symptom screens
    data object SymptomConfirmation : Route("symptom_confirmation")
    
    // Education screens
    data object ArticleDetail : Route("article_detail")
}

/*
* "Strings mágicos" se refiere a cuando pones un texto duro y repetido en varias partes del código,
* Si mañana cambias "home" por "inicio", tendrías que buscar todas las ocurrencias de "home" a mano.
* Eso es frágil y propenso a errores.
La idea es: mejor centralizar esos strings en una sola clase (Route), y usarlos desde ahí.*/