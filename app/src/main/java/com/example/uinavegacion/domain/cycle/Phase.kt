package com.example.uinavegacion.domain.cycle

/**
 * Representa las diferentes fases del ciclo menstrual.
 */
enum class Phase {
    MENSTRUATION,    // Fase de sangrado menstrual
    FOLLICULAR,      // Fase folicular (después de menstruación hasta ovulación)
    OVULATION,       // Día de ovulación
    LUTEAL,         // Fase lútea (post-ovulación hasta próxima menstruación)
    UNKNOWN         // Estado desconocido o no calculable
}