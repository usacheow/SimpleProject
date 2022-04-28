package com.usacheow.simpleapp.notification

import android.content.Intent
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.usacheow.coreui.notification.NotificationHelper
import com.usacheow.simpleapp.mainscreen.AppActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FirebaseNotificationsService : FirebaseMessagingService() {

    @Inject lateinit var notificationHelper: NotificationHelper

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        sendNotification(remoteMessage)
    }

    private fun sendNotification(remoteMessage: RemoteMessage) {
        val notificationIntent = Intent(this, AppActivity::class.java)
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