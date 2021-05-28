package com.usacheow.simpleapp.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.usacheow.coredata.database.SettingsStorage
import java.util.Calendar

private const val MILLIS_SECONDS_IN_DAY = 24 * 60 * 60 * 1000L

class AlarmHelper(
    private val settingsStorage: SettingsStorage
) {

    fun startAlarm(context: Context) {
        val timeInMillis = Calendar.getInstance().timeInMillis + MILLIS_SECONDS_IN_DAY
        val intent = Intent(context, AlarmReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        alarmManager.set(AlarmManager.RTC, timeInMillis, pendingIntent)
    }
}