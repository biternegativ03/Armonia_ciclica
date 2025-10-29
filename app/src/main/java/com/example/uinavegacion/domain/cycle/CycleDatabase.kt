package com.example.uinavegacion.domain.cycle

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [
        CycleStartEntity::class,
        UserCyclePrefsEntity::class
    ],
    version = 1
)
@TypeConverters(DateConverters::class)
abstract class CycleDatabase : RoomDatabase() {
    abstract fun cycleDao(): CycleDao
}