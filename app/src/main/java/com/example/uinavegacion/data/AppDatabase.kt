package com.example.uinavegacion.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.uinavegacion.domain.cycle.CycleConverters
import com.example.uinavegacion.domain.cycle.CycleDao
import com.example.uinavegacion.domain.cycle.CycleStartEntity
import com.example.uinavegacion.domain.cycle.UserCyclePrefsEntity

@Database(
    entities = [
        CycleStartEntity::class,
        UserCyclePrefsEntity::class
    ],
    version = 1
)
@TypeConverters(CycleConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cycleDao(): CycleDao
}