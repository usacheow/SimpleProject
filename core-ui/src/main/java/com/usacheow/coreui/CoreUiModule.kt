package com.usacheow.coreui

import com.usacheow.coreui.helper.NotificationHelper
import com.usacheow.coreui.helper.NotificationHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(
    includes = [SystemServiceModule::class]
)
@InstallIn(SingletonComponent::class)
interface CoreUiModule {

    @Binds
    @Singleton
    fun notificationHelper(helper: NotificationHelperImpl): NotificationHelper
}