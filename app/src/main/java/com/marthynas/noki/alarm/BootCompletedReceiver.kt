package com.marthynas.noki.alarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BootCompletedReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == "android.intent.action.BOOT_COMPLETED") {
            // Start the service when the device boots up
            println("Boot completed, rescheduling alarms.")

            val scope = CoroutineScope(Dispatchers.IO)

            scope.launch {

            }
        }

    }
}