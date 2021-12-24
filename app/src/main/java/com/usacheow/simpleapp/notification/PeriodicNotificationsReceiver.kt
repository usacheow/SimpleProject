package com.usacheow.simpleapp.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.usacheow.core.DurationValue
import com.usacheow.coreui.helper.NotificationHelper
import com.usacheow.simpleapp.mainscreen.MainScreenActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
import javax.inject.Inject
import kotlin.time.ExperimentalTime

@AndroidEntryPoint
@OptIn(ExperimentalTime::class)
class PeriodicNotificationsReceiver : BroadcastReceiver() {

    @Inject lateinit var notificationHelper: NotificationHelper

    companion object {
        fun startPeriodicNotifications(context: Context) {
            val timeInMillis = Calendar.getInstance().timeInMillis + DurationValue.DAY.inWholeMilliseconds
            val intent = Intent(context, PeriodicNotificationsReceiver::class.java)
            val pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.set(AlarmManager.RTC, timeInMillis, pendingIntent)
        }
    }

    override fun onReceive(context: Context, intent: Intent) {
        val notificationIntent = Intent(context, MainScreenActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        val model = NotificationHelper.Model(
            title = "Title",
            text = "Message",
            intent = notificationIntent
        )

        notificationHelper.showSimpleNotification(model)
    }
}