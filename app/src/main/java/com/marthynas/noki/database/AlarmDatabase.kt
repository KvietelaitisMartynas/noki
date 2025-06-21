package com.marthynas.noki.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.marthynas.noki.daos.AlarmDao
import com.marthynas.noki.entities.Alarm

@Database(
    entities = [Alarm::class],
    version = 1
)

abstract class AlarmDatabase: RoomDatabase() {
    abstract val dao: AlarmDao
}