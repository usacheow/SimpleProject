package com.usacheow.simpleapp.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.usacheow.coreui.utils.NotificationHelper
import com.usacheow.simpleapp.mainscreen.MainScreenActivity
import java.util.Calendar

private const val MILLIS_SECONDS_IN_DAY = 24 * 60 * 60 * 1000L

class PeriodicNotificationsReceiver : BroadcastReceiver() {

    companion object {
        fun startPeriodicNotifications(context: Context) {
            val timeInMillis = Calendar.getInstance().timeInMillis + MILLIS_SECONDS_IN_DAY
            val intent = Intent(context, PeriodicNotificationsReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.set(AlarmManager.RTC, timeInMillis, pendingIntent)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        val notificationIntent = Intent(context, MainScreenActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        val model = NotificationHelper.NotificationModel(
            title = "Title",
            text = "Message",
            intent = notificationIntent
        )

        NotificationHelper(context).showSimpleNotification(model)
    }
}