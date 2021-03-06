package com.usacheow.apptest.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.usacheow.apptest.R

private const val APP_PACKAGE = "com.example.myapplication"
private const val APP_INFO_CHANEL_ID = "$APP_PACKAGE.APP_INFO_CHANNEL"

private const val SIMPLE_NOTIFICATION_ID = 221

class NotificationHelper(
    private val context: Context
) {

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val appInfoChannel = NotificationChannel(
                APP_INFO_CHANEL_ID,
                "Channel Name",
                NotificationManager.IMPORTANCE_DEFAULT,
            ).apply {
                description = "Channel description"
                setShowBadge(true)
            }

            notificationManager.createNotificationChannels(listOf(appInfoChannel))
        }
    }

    private val notificationManager
        get() = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun startNotificationsSettingsScreen() {
        val citiesChannelIntent = Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS).apply {
            putExtra(Settings.EXTRA_CHANNEL_ID, APP_INFO_CHANEL_ID)
            putExtra(Settings.EXTRA_APP_PACKAGE, APP_PACKAGE)
        }
        context.startActivity(citiesChannelIntent)
    }

    fun showSimpleNotification(model: NotificationModel) {
        val notification = createNotification(model).build()
        notificationManager.notify(SIMPLE_NOTIFICATION_ID, notification)
    }

    private fun createNotification(model: NotificationModel) = with(model) {
        val pendingIntent = PendingIntent.getActivity(
            context,
            SIMPLE_NOTIFICATION_ID,
            model.intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val style = NotificationCompat.BigTextStyle()
            .bigText(model.text)
        NotificationCompat.Builder(context, channelId)
            .setStyle(style)
            .setSmallIcon(smallIcon)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, largeIcon))
            .setContentTitle(title)
            .setContentText(text)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
    }

    data class NotificationModel(
        val title: String? = null,
        val channelId: String = APP_INFO_CHANEL_ID,
        val smallIcon: Int = R.drawable.ic_android,
        val largeIcon: Int = R.mipmap.ic_launcher,
        val text: String? = null,
        val intent: Intent
    )
}