package com.marthynas.noki.alarm

import com.marthynas.noki.entities.Alarm

data class AlarmState(
    val alarms: List<Alarm> = emptyList(),
    val hour: Int = 0,
    val minute: Int = 0,
    val label: String = "",
    val snooze: Boolean = false,
    val repeatDays: Int = 0,
    val isAddingAlarm: Boolean = false,
) {

}