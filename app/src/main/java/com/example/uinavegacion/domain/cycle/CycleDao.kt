package com.example.uinavegacion.domain.cycle

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CycleDao {
    @Query("SELECT * FROM cycle_starts ORDER BY date DESC")
    suspend fun getAllCycleStarts(): List<CycleStartEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCycleStart(cycleStart: CycleStartEntity)

    @Query("SELECT * FROM user_cycle_prefs WHERE id = 1")
    fun getCyclePrefs(): Flow<UserCyclePrefsEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCyclePrefs(prefs: UserCyclePrefsEntity)
}