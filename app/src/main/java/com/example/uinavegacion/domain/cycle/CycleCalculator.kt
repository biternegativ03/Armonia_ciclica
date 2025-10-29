package com.example.uinavegacion.domain.cycle

import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.math.roundToInt

/**
 * Calculador de ciclo menstrual que implementa la lógica core del tracking.
 * Todas las funciones son puras (sin efectos secundarios) para facilitar testing.
 */
object CycleCalculator {
    // Implementación reducida / stub para MVP visual.
    // Mantiene las mismas firmas pero evita lógica compleja.

    /**
     * Devuelve una fecha predecida simplificada: lastPeriodStart + avgCycleLength
     * (cálculo mínimo, seguro y determinista para la UI).
     */
    fun predictedNextPeriodStart(p: CycleParams): LocalDate {
        return p.lastPeriodStart.plusDays(p.avgCycleLength.toLong())
    }

    /**
     * Devuelve un día de ovulación simplificado: último inicio + lutealLength
     * (no hay heurísticas avanzadas).
     */
    fun predictedOvulationDay(p: CycleParams): LocalDate {
        return p.lastPeriodStart.plusDays((p.avgCycleLength - p.lutealLength).toLong())
    }

    /**
     * Implementación placeholder: prioriza evidencia observada si existe,
     * en caso contrario devuelve la predicción simplificada.
     */
    fun ovulationDay(p: CycleParams): LocalDate {
        return p.ovulationObserved() ?: predictedOvulationDay(p)
    }

    /**
     * Devuelve una ventana fértil simplificada alrededor de la fecha de ovulación.
     */
    fun fertileWindow(p: CycleParams): FertileWindow {
        val ovulation = ovulationDay(p)
        return FertileWindow(
            start = ovulation.minusDays(2), // ventana reducida y sencilla
            peak = ovulation,
            end = ovulation.plusDays(1)
        )
    }

    /**
     * Versión conservadora: devuelve UNKNOWN salvo cuando la fecha cae
     * claramente dentro del periodo registrado.
     */
    fun phaseForDate(p: CycleParams, date: LocalDate): Phase {
        val mensesEnd = p.lastPeriodStart.plusDays(p.mensesLength.toLong())
        return when {
            date >= p.lastPeriodStart && date < mensesEnd -> Phase.MENSTRUATION
            else -> Phase.UNKNOWN
        }
    }

    /**
     * Versión simplificada del día de ciclo: diferencia mínima con lastPeriodStart.
     */
    fun dayOfCycle(p: CycleParams, date: LocalDate): Int {
        return ChronoUnit.DAYS.between(p.lastPeriodStart, date).toInt().coerceAtLeast(1)
    }

    /**
     * Recalcula promedios de forma trivial: devuelve 28 días por defecto.
     */
    fun recalcAverages(periodStarts: List<LocalDate>): Pair<Int, Int?> {
        return Pair(28, null)
    }
}