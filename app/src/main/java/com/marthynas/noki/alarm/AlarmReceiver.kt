package com.marthynas.noki.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build

class AlarmReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        val alarmId = intent?.getIntExtra("ALARM_ID", -1)
        val label = intent?.getStringExtra("ALARM_LABEL") ?: "Alarm"

        println("ALARM TRIGGERED: ID = $alarmId, LABEL = $label")

        val serviceIntent = Intent(context, AlarmService::class.java).apply {
            putExtra("ALARM_ID", alarmId)
            putExtra("ALARM_LABEL", label)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (context != null) {
                context.startForegroundService(serviceIntent)
            }
        } else {
            if (context != null) {
                context.startService(serviceIntent)
            }
        }
    }
}