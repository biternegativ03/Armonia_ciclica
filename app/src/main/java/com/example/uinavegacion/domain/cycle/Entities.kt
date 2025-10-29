package com.example.uinavegacion.domain.cycle

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "cycle_starts")
data class CycleStartEntity(
    @PrimaryKey val date: LocalDate
)

@Entity(tableName = "user_cycle_prefs")
data class UserCyclePrefsEntity(
    @PrimaryKey val id: Int = 1, // Singleton
    val lastPeriodStart: LocalDate? = null,
    val avgCycleLength: Int = 28,
    val lutealLength: Int = 14,
    val mensesLength: Int = 5,
    val ovulationPositiveOpkDate: LocalDate? = null,
    val bbtRiseDate: LocalDate? = null
)