package com.usacheow.basesources

import android.os.Build
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.tasks.await

interface DeviceInfoProvider {

    fun getDeviceName(): String

    suspend fun getDeviceId(): String

    suspend fun getPushToken(): String
}

class DeviceInfoProviderImpl() : DeviceInfoProvider {

    override fun getDeviceName(): String {
        return "${Build.BRAND} ${Build.MODEL}"
    }

    override suspend fun getDeviceId(): String {
        return FirebaseInstallations.getInstance().id.await()
    }

    override suspend fun getPushToken(): String {
        return FirebaseMessaging.getInstance().token.await()
    }
}