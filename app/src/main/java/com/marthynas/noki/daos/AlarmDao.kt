package com.marthynas.noki.daos

import androidx.room.*
import com.marthynas.noki.entities.Alarm
import kotlinx.coroutines.flow.Flow

@Dao
interface AlarmDao {

    @Upsert
    suspend fun upsertAlarm(alarm: Alarm)

    //the alarms will be sorted by time by default
    @Query ("SELECT * FROM alarms ORDER BY hour, minute ASC")
    fun getAllAlarms(): Flow<List<Alarm>>

    @Delete
    fun deleteAlarm(alarm: Alarm)

    @Update
    fun updateAlarm(alarm: Alarm)

}