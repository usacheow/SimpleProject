package com.usacheow.simpleapp.appupdate

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface AppUpdateModule {

    @Binds
    @Singleton
    fun appUpdateProvider(provider: AppUpdateProviderImpl): AppUpdateProvider

    @Binds
    @Singleton
    fun updateRouter(provider: AppUpdateProvider): UpdateRouter
}