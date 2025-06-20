package com.marthynas.noki.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarms")
data class Alarm(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val hour: Int,
    val minute: Int,
    val label: String,
    val snooze: Boolean,
    val repeatDays: Int = 0
) {
    companion object {
        const val MONDAY = 1
        const val TUESDAY = 2
        const val WEDNESDAY = 4
        const val THURSDAY = 8
        const val FRIDAY = 16
        const val SATURDAY = 32
        const val SUNDAY = 64

        const val NONE = 0
        const val EVERY_DAY = MONDAY or TUESDAY or WEDNESDAY or THURSDAY or FRIDAY or SATURDAY or SUNDAY

        fun isDaySet(repeatDays: Int, day: Int): Boolean {
            return (repeatDays and day) != 0
        }

        fun setDay(currentMask: Int, dayFlag: Int, shouldSet: Boolean): Int {
            return if (shouldSet) {
                currentMask or dayFlag
            } else {
                currentMask and dayFlag.inv()
            }
        }
    }
}