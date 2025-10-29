package com.example.uinavegacion.domain.cycle

import java.time.LocalDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

/**
 * Servicio que integra el calculador de ciclo con persistencia.
 * Expone funciones de alto nivel para la UI.
 */
class CycleService(
    private val cycleDao: CycleDao,
    private val prefsRepository: CyclePrefsRepository
) {
    /**
     * Obtiene la fase actual del ciclo.
     */
    suspend fun todayPhase(): Phase {
        // Stub: devolver un estado neutral para la UI visual.
        // Evitar llamadas a la capa de persistencia en este MVP visual.
        return Phase.UNKNOWN
    }

    /**
     * Obtiene la fecha predicha del próximo período.
     */
    suspend fun nextPeriod(): LocalDate? {
        // Stub: no realizar cálculos complejos — devolver null para indicar "sin predicción".
        return null
    }

    /**
     * Obtiene la ventana fértil del ciclo actual.
     */
    suspend fun fertileWindowCurrent(): FertileWindow? {
        // Stub: no calcular ventana fértil en esta versión visual.
        return null
    }

    /**
     * Calcula el día del ciclo para una fecha específica.
     */
    suspend fun dayOfCycle(date: LocalDate): Int {
        // Stub: devolver 0 si no hay dato real disponible.
        return 0
    }

    /**
     * Registra el inicio de un nuevo período y recalcula promedios.
     */
    suspend fun registerNewPeriod(start: LocalDate) {
        // Stub: no persistir cambios en MVP visual. Dejar como no-op.
        return
    }

    /**
     * Registra evidencia de ovulación (OPK y/o BBT).
     */
    suspend fun setObservedOvulation(opkDate: LocalDate? = null, bbtRise: LocalDate? = null) {
        // Stub: no almacenar evidencia observada en MVP visual.
        return
    }

    private suspend fun getCurrentParams(): CycleParams? {
        // Stub: deshabilitar lectura de persistencia para MVP visual.
        return null
    }
}