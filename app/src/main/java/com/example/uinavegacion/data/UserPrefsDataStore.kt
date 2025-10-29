package com.example.uinavegacion.data

import android.content.Context
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import java.time.LocalDate

val Context.userPrefsDataStore by preferencesDataStore("user_prefs")

object UserPrefsKeys {
    val LAST_PERIOD = stringPreferencesKey("last_period")      // ISO-8601 (YYYY-MM-DD)
    val AVG_CYCLE   = intPreferencesKey("avg_cycle")           // 28 por defecto
    val LUTEAL_LEN  = intPreferencesKey("luteal_len")          // 14 por defecto
    val MENSES_LEN  = intPreferencesKey("menses_len")          // 5 por defecto
}

class UserPrefsRepo(private val context: Context) {
    val flow = context.userPrefsDataStore.data.map { p ->
        val last = p[UserPrefsKeys.LAST_PERIOD]?.let(LocalDate::parse)
        UserPrefs(
            lastPeriodStart = last,
            avgCycleLength = p[UserPrefsKeys.AVG_CYCLE] ?: 28,
            lutealLength   = p[UserPrefsKeys.LUTEAL_LEN] ?: 14,
            mensesLength   = p[UserPrefsKeys.MENSES_LEN] ?: 5
        )
    }

    suspend fun setLastPeriod(date: LocalDate) {
        context.userPrefsDataStore.edit { it[UserPrefsKeys.LAST_PERIOD] = date.toString() }
    }

    suspend fun setCycleLengths(avg: Int, luteal: Int, menses: Int) {
        context.userPrefsDataStore.edit {
            it[UserPrefsKeys.AVG_CYCLE] = avg
            it[UserPrefsKeys.LUTEAL_LEN] = luteal
            it[UserPrefsKeys.MENSES_LEN] = menses
        }
    }
}

data class UserPrefs(
    val lastPeriodStart: LocalDate?,
    val avgCycleLength: Int,
    val lutealLength: Int,
    val mensesLength: Int
)
