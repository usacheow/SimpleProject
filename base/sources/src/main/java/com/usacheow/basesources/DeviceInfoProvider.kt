package com.usacheow.basesources

import android.os.Build
import com.google.firebase.installations.FirebaseInstallations
import com.google.firebase.messaging.FirebaseMessaging
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

interface DeviceInfoProvider {

    fun getDeviceName(): String

    suspend fun getDeviceId(): String

    suspend fun getPushToken(): String
}

class DeviceInfoProviderImpl @Inject constructor() : DeviceInfoProvider {

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

@Module
@InstallIn(SingletonComponent::class)
interface DeviceInfoProviderModule {

    @Binds
    @Singleton
    fun deviceInfoProvider(provider: DeviceInfoProviderImpl): DeviceInfoProvider
}