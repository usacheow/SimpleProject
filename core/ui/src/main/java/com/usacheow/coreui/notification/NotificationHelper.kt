package com.usacheow.coreui.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import com.usacheow.coreuitheme.R as CoreUiThemeR

interface NotificationHelper {

    fun showSimpleNotification(model: Model)

    data class Model(
        val id: Int = 1,
        val channelId: Channels = Channels.Default,
        val title: String? = null,
        val smallIcon: Int = CoreUiThemeR.drawable.ic_logo,
        val largeIcon: Int = CoreUiThemeR.mipmap.ic_launcher,
        val text: String? = null,
        val intent: Intent,
    )

    enum class Channels(val channelName: String, val channelDescription: String) {
        Default("Default", "Default channel");

        fun getId(context: Context) = "${context.packageName}_$name"
    }
}

class NotificationHelperImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val notificationManager: NotificationManager,
) : NotificationHelper {

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channels = NotificationHelper.Channels.values().map {
                NotificationChannel(it.getId(context), it.channelName, NotificationManager.IMPORTANCE_DEFAULT).apply {
                    description = it.channelDescription
                }
            }

            notificationManager.createNotificationChannels(channels)
        }
    }

    override fun showSimpleNotification(model: NotificationHelper.Model) {
        notificationManager.notify(
            model.id,
            model.toNotificationBuilder().build(),
        )
    }

    private fun NotificationHelper.Model.toNotificationBuilder(): NotificationCompat.Builder {
        val style = NotificationCompat.BigTextStyle().bigText(text)
        val pendingIntent = PendingIntent.getActivity(
            context,
            id,
            intent,
            PendingIntent.FLAG_IMMUTABLE,
        )

        return NotificationCompat.Builder(context, channelId.getId(context))
            .setStyle(style)
            .setSmallIcon(smallIcon)
            .setLargeIcon(BitmapFactory.decodeResource(context.resources, largeIcon))
            .setContentTitle(title)
            .setContentText(text)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
    }
}