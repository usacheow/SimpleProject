package  com.usacheow.simpleapp.notification

import android.content.Intent
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.usacheow.simpleapp.mainscreen.MainScreenActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

const val MESSAGE_KEY = "MESSAGE_KEY"

@AndroidEntryPoint
class MyFirebaseMessagingService : FirebaseMessagingService() {

    @Inject lateinit var notificationHelper: NotificationHelper

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        sendNotification(remoteMessage)
    }

    private fun sendNotification(remoteMessage: RemoteMessage) {
        remoteMessage.data[MESSAGE_KEY]

        val notificationIntent = Intent(this, MainScreenActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        val model = NotificationHelper.NotificationModel(
            title = remoteMessage.notification?.title ?: "",
            text = remoteMessage.notification?.body ?: "",
            intent = notificationIntent
        )

        notificationHelper.showSimpleNotification(model)
    }
}