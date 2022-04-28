package com.usacheow.coredata.location

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface LocationProviderModule {

    @Binds
    @Singleton
    fun locationProvider(provider: LocationProviderImpl): LocationProvider
}