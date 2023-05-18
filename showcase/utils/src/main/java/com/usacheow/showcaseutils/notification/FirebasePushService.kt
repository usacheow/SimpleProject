package com.usacheow.showcaseutils.notification

import android.content.Intent
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.kodein.di.DI
import org.kodein.di.instance

class FirebasePushService : FirebaseMessagingService() {

    private val di = DI {
        importOnce(notificationHelperDiModule)
    }

    private val notificationHelper: NotificationHelper by di.instance()

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        sendNotification(remoteMessage)
    }

    private fun sendNotification(remoteMessage: RemoteMessage) {
        val notificationIntent = Intent("")
            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        val model = NotificationHelper.Model(
            title = remoteMessage.notification?.title.orEmpty(),
            text = remoteMessage.notification?.body.orEmpty(),
            intent = notificationIntent,
        )

        notificationHelper.showSimpleNotification(model)
    }
//    example
//    private suspend fun getPushToken(): String = suspendCoroutine { continuation ->
//        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener { instanceIdResult ->
//            continuation.resume(instanceIdResult.token)
//        }
//    }
}