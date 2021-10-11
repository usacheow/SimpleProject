package com.usacheow.coreui

import android.app.NotificationManager
import android.content.Context
import android.hardware.SensorManager
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SystemServiceModule {

    @Provides
    @Singleton
    fun connectivityManager(
        @ApplicationContext context: Context,
    ) = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Provides
    @Singleton
    fun sensorManager(
        @ApplicationContext context: Context,
    ) = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    @Provides
    @Singleton
    fun notificationManager(
        @ApplicationContext context: Context,
    ) = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
}