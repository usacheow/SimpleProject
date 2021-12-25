package com.usacheow.coreui.helper

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.usacheow.core.resource.ResourcesWrapper
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import com.usacheow.coreui.R as CoreUiR

private const val APP_PACKAGE = "com.usacheow.simpleapp"
private const val APP_INFO_CHANNEL_ID = "$APP_PACKAGE.APP_INFO_CHANNEL"

private const val SIMPLE_NOTIFICATION_ID = 221

interface NotificationHelper {

    @RequiresApi(api = Build.VERSION_CODES.O)
    fun startNotificationsSettingsScreen()

    fun showSimpleNotification(model: Model)

    data class Model(
        val title: String? = null,
        val channelId: String = APP_INFO_CHANNEL_ID,
        val smallIcon: Int = CoreUiR.drawable.ic_user,
        val largeIcon: Int = CoreUiR.mipmap.ic_launcher,
        val text: String? = null,
        val intent: Intent
    )
}

class NotificationHelperImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val notificationManager: NotificationManager,
    private val resources: ResourcesWrapper,
) : NotificationHelper {

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val appInfoChannel = NotificationChannel(
                APP_INFO_CHANNEL_ID,
                "Channel Name",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Channel description"
                setShowBadge(true)
            }

            notificationManager.createNotificationChannels(listOf(appInfoChannel))
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    override fun startNotificationsSettingsScreen() {
        val citiesChannelIntent = Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS).apply {
            putExtra(Settings.EXTRA_CHANNEL_ID, APP_INFO_CHANNEL_ID)
            putExtra(Settings.EXTRA_APP_PACKAGE, APP_PACKAGE)
        }
        context.startActivity(citiesChannelIntent)
    }

    override fun showSimpleNotification(model: NotificationHelper.Model) {
        val notification = createNotification(model).build()
        notificationManager.notify(SIMPLE_NOTIFICATION_ID, notification)
    }

    private fun createNotification(model: NotificationHelper.Model) = with(model) {
        val pendingIntent = PendingIntent.getActivity(
            context,
            SIMPLE_NOTIFICATION_ID,
            model.intent,
            PendingIntent.FLAG_IMMUTABLE,
        )

        val style = NotificationCompat.BigTextStyle()
            .bigText(model.text)
        NotificationCompat.Builder(context, channelId)
            .setStyle(style)
            .setSmallIcon(smallIcon)
            .setLargeIcon(resources.getBitmap(largeIcon))
            .setContentTitle(title)
            .setContentText(text)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
    }
}