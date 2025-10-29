package com.example.uinavegacion.domain.cycle

import java.time.LocalDate

/**
 * Parámetros del ciclo menstrual y evidencia observada.
 *
 * @property lastPeriodStart Primer día del último sangrado menstrual
 * @property avgCycleLength Duración promedio del ciclo completo (default: 28 días)
 * @property lutealLength Duración de la fase lútea (default: 14 días, más estable que el ciclo completo)
 * @property mensesLength Duración del sangrado menstrual (default: 5 días)
 * @property ovulationPositiveOpkDate Fecha de OPK positivo (ovulación ocurre ~1 día después)
 * @property bbtRiseDate Fecha de alza de temperatura basal (ovulación ocurrió ~1 día antes)
 * @property periodStarts Historial de inicios de periodos (ordenado, últimos 3-6)
 */
data class CycleParams(
    val lastPeriodStart: LocalDate,
    val avgCycleLength: Int = 28,
    val lutealLength: Int = 14,
    val mensesLength: Int = 5,
    val ovulationPositiveOpkDate: LocalDate? = null,
    val bbtRiseDate: LocalDate? = null,
    val periodStarts: List<LocalDate> = emptyList()
) {
    init {
        require(avgCycleLength in 21..45) { "La duración del ciclo debe estar entre 21 y 45 días" }
        require(lutealLength in 10..16) { "La fase lútea debe estar entre 10 y 16 días" }
        require(mensesLength in 2..8) { "La menstruación debe durar entre 2 y 8 días" }
    }

    /**
     * Determina la fecha de ovulación observada basada en evidencia.
     * Prioriza OPK sobre BBT si ambos están presentes.
     */
    fun ovulationObserved(): LocalDate? = when {
        ovulationPositiveOpkDate != null -> ovulationPositiveOpkDate.plusDays(1)
        bbtRiseDate != null -> bbtRiseDate.minusDays(1)
        else -> null
    }
}