package com.example.uinavegacion.domain.cycle

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

/**
 * Repositorio que gestiona las preferencias del ciclo menstrual.
 * Utiliza Room como fuente de datos.
 */
class CyclePrefsRepository(private val cycleDao: CycleDao) {
    
    /**
     * Obtiene las preferencias actuales del ciclo.
     */
    fun getCyclePrefs(): Flow<UserCyclePrefsEntity?> {
        return cycleDao.getCyclePrefs()
    }

    /**
     * Actualiza las preferencias del ciclo.
     * @param transform función que recibe las preferencias actuales y retorna las nuevas
     */
    suspend fun updatePrefs(transform: (UserCyclePrefsEntity) -> UserCyclePrefsEntity) {
        cycleDao.getCyclePrefs().map { it ?: DEFAULT_PREFS }.collect { prefs ->
            cycleDao.updateCyclePrefs(transform(prefs))
        }
    }

    /**
     * Inicializa las preferencias con valores por defecto si no existen.
     */
    suspend fun initializeIfNeeded() {
        cycleDao.getCyclePrefs().map { it == null }.collect { isEmpty ->
            if (isEmpty) {
                cycleDao.updateCyclePrefs(DEFAULT_PREFS)
            }
        }
    }

    companion object {
        private val DEFAULT_PREFS = UserCyclePrefsEntity(
            id = 1,
            avgCycleLength = 28,
            lutealLength = 14,
            mensesLength = 5
        )
    }
}