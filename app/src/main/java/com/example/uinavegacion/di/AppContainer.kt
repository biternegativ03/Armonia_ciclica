package com.example.uinavegacion.di

import android.content.Context
import androidx.room.Room
import com.example.uinavegacion.data.AppDatabase
import com.example.uinavegacion.domain.cycle.CyclePrefsRepository
import com.example.uinavegacion.domain.cycle.CycleService

/**
 * Proveedor de dependencias para la aplicación.
 * Implementa el patrón Service Locator.
 */
object AppContainer {
    private var database: AppDatabase? = null
    private var cycleService: CycleService? = null

    fun provideCycleService(context: Context): CycleService {
        return cycleService ?: synchronized(this) {
            cycleService ?: createCycleService(context).also { cycleService = it }
        }
    }

    private fun createCycleService(context: Context): CycleService {
        val db = getDatabase(context)
        val cycleDao = db.cycleDao()
        val prefsRepository = CyclePrefsRepository(cycleDao)
        return CycleService(cycleDao, prefsRepository)
    }

    private fun getDatabase(context: Context): AppDatabase {
        return database ?: synchronized(this) {
            database ?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "armonia_ciclica.db"
            ).build().also { database = it }
        }
    }

    fun reset() {
        database = null
        cycleService = null
    }
}