package com.usacheow.coreui.notification

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NotificationHelperModule {

    @Binds
    @Singleton
    fun notificationHelper(helper: NotificationHelperImpl): NotificationHelper
}