package com.marthynas.noki.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.marthynas.noki.alarm.AlarmEvent
import com.marthynas.noki.alarm.AlarmState
import com.marthynas.noki.daos.AlarmDao
import com.marthynas.noki.entities.Alarm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AlarmViewModel(
    private val alarmDao: AlarmDao
) : ViewModel() {
    private val _alarms = alarmDao.getAllAlarms()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    private val _state = MutableStateFlow(AlarmState())
    val state = combine(_state, _alarms) { state, alarms ->
        state.copy(
            alarms = alarms
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), AlarmState())

    fun onEvent(event: AlarmEvent) {
        when (event) {
            is AlarmEvent.DeleteAlarm -> {
                viewModelScope.launch {
                    alarmDao.deleteAlarm(event.alarm)
                }
            }

            AlarmEvent.HideDialog -> {
                _state.update {
                    it.copy(
                        isAddingAlarm = false
                    )
                }
            }

            AlarmEvent.SaveAlarm -> {
                val hour = state.value.hour
                val minute = state.value.minute
                val label = state.value.label
                val snooze = state.value.snooze
                val repeatDays = state.value.repeatDays

                val finalLabel = if (label.isBlank()) "alarm" else label

                val alarm = Alarm(
                    hour = hour,
                    minute = minute,
                    label = finalLabel,
                    snooze = snooze,
                    repeatDays = repeatDays
                )
                viewModelScope.launch {
                    alarmDao.upsertAlarm(alarm)
                }
                _state.update {
                    it.copy(
                        isAddingAlarm = false,
                        hour = 0,
                        minute = 0,
                    )
                }
            }

            is AlarmEvent.SetHour -> {
                _state.update {
                    it.copy(
                        hour = event.hour
                    )
                }
            }

            is AlarmEvent.SetLabel -> {
                _state.update {
                    it.copy(
                        label = event.label
                    )
                }
            }

            is AlarmEvent.SetMinute -> {
                _state.update {
                    it.copy(
                        minute = event.minute
                    )
                }
            }

            is AlarmEvent.SetRepeatDays -> {
                _state.update {
                    it.copy(
                        repeatDays = event.repeatDays
                    )
                }
            }

            is AlarmEvent.SetSnooze -> {
                _state.update {
                    it.copy(
                        snooze = event.snooze
                    )
                }
            }

            AlarmEvent.ShowDialog -> {
                _state.update {
                    it.copy(
                        isAddingAlarm = true
                    )
                }
            }

            is AlarmEvent.ToggleRepeatDay -> {
                _state.update { state ->
                    val currentMask = state.repeatDays
                    val dayFlag = event.dayFlag
                    val isCurrentlySet = (currentMask and dayFlag) != 0

                    val isSet = Alarm.isDaySet(currentMask, dayFlag)
                    val newMask = Alarm.setDay(currentMask, dayFlag, !isSet)

                    state.copy(repeatDays = newMask)
                }
            }
        }
    }

}