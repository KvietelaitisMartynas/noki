package com.marthynas.noki.alarm

import com.marthynas.noki.entities.Alarm

sealed interface AlarmEvent {
    object SaveAlarm: AlarmEvent
    data class SetHour(val hour: Int): AlarmEvent
    data class SetMinute(val minute: Int): AlarmEvent
    data class SetLabel(val label: String): AlarmEvent
    data class SetSnooze(val snooze: Boolean): AlarmEvent

    data class ToggleRepeatDay(val dayFlag: Int): AlarmEvent
    data class SetRepeatDays(val repeatDays: Int): AlarmEvent

    object ShowDialog: AlarmEvent
    object HideDialog: AlarmEvent

    data class DeleteAlarm(val alarm: Alarm): AlarmEvent
}