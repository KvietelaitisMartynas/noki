package com.marthynas.noki.alarm

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.os.IBinder
import com.marthynas.noki.AlarmActivity
import com.marthynas.noki.MainActivity // Change to your "Alarm Ringing" activity if you have one
import com.marthynas.noki.R // Import your app's R file

class AlarmService : Service() {
    private lateinit var ringtone: Ringtone
    private val NOTIFICATION_ID = 1
    private val CHANNEL_ID = "ALARM_SERVICE_CHANNEL"

    override fun onCreate() {
        super.onCreate()

        val alarmUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM) ?:
        run {
            RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        }
        ringtone = RingtoneManager.getRingtone(this, alarmUri)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val alarmLabel = intent?.getStringExtra("ALARM_LABEL") ?: "Alarm"

        createNotificationChannel()

        // *** THIS IS THE CRUCIAL CHANGE ***
        // Create an intent that will open your AlarmActivity
        val fullScreenIntent = Intent(this, AlarmActivity::class.java).apply {
            // Add flags to bring the activity to the front
            this.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val fullScreenPendingIntent = PendingIntent.getActivity(
            this, 0, fullScreenIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = Notification.Builder(this, CHANNEL_ID)
            .setContentTitle("Alarm!")
            .setContentText(alarmLabel)
            .setSmallIcon(R.drawable.ic_launcher_foreground) // Use a real icon
            .setCategory(Notification.CATEGORY_ALARM)
            // THIS IS THE KEY METHOD
            .setFullScreenIntent(fullScreenPendingIntent, true)
            // You can also add a content intent for when the user taps the notification
            // itself after the initial launch.
            .setContentIntent(fullScreenPendingIntent)
            .build()

        startForeground(NOTIFICATION_ID, notification)

        ringtone.play()
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        if(::ringtone.isInitialized && ringtone.isPlaying){
            ringtone.stop()
        }
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Alarm Service Channel",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Channel for Alarm Service"
                setSound(null, null) // We manage our own sound
            }

            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }
}