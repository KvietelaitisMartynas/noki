package com.marthynas.noki

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.marthynas.noki.alarm.AlarmService

class AlarmActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm)

        // These flags are crucial for showing the activity over the lock screen
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1) {
            setShowWhenLocked(true)
            setTurnScreenOn(true)
        } else {
            window.addFlags(
                WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                        or WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                        or WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON
            )
        }

        val dismissButton: Button = findViewById(R.id.dismissButton)
        dismissButton.setOnClickListener {
            dismissAlarm()
        }
    }

    private fun dismissAlarm() {
        // Stop the service which in turn stops the ringtone
        val intent = Intent(this, AlarmService::class.java)
        stopService(intent)

        // Finish this activity
        finish()
    }
}