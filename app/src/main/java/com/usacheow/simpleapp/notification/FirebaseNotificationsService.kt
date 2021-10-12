package com.usacheow.simpleapp.notification

import android.content.Intent
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.usacheow.coreui.helper.NotificationHelper
import com.usacheow.simpleapp.mainscreen.MainScreenActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

const val MESSAGE_KEY = "MESSAGE_KEY"

@AndroidEntryPoint
class FirebaseNotificationsService : FirebaseMessagingService() {

    @Inject lateinit var notificationHelper: NotificationHelper

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        sendNotification(remoteMessage)
    }

    private fun sendNotification(remoteMessage: RemoteMessage) {
        remoteMessage.data[MESSAGE_KEY]

        val notificationIntent = Intent(this, MainScreenActivity::class.java)
            .addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
        val model = NotificationHelper.Model(
            title = remoteMessage.notification?.title ?: "",
            text = remoteMessage.notification?.body ?: "",
            intent = notificationIntent
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