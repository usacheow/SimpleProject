package com.usacheow.coredata

import android.content.Context
import com.usacheow.coredata.cache.CacheCleaner
import com.usacheow.coredata.cache.CacheProvider
import com.usacheow.coredata.cache.LruCacheProvider
import com.usacheow.coredata.database.AppDatabase
import com.usacheow.coredata.featuretoggle.EditableFeatureToggle
import com.usacheow.coredata.featuretoggle.FeatureToggle
import com.usacheow.coredata.featuretoggle.FeatureToggleImpl
import com.usacheow.coredata.featuretoggle.LocalFeatureToggleStorage
import com.usacheow.coredata.featuretoggle.RemoteFeatureToggleStorage
import com.usacheow.coredata.location.LocationProvider
import com.usacheow.coredata.location.LocationProviderImpl
import com.usacheow.coredata.network.NetworkStateProvider
import com.usacheow.coredata.network.NetworkStateProviderImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(
    includes = [
        NetworkModule::class,
        CoroutineModule::class,
        BindsModule::class,
    ]
)
@InstallIn(SingletonComponent::class)
class CoreDataModule {

    @Provides
    @Singleton
    fun appDatabase(@ApplicationContext context: Context): AppDatabase = AppDatabase.newInstance(context)

    @Provides
    @Singleton
    fun editableFeatureToggle(
        remoteFeatureToggleStorage: RemoteFeatureToggleStorage,
        localFeatureToggleStorage: LocalFeatureToggleStorage,
    ): EditableFeatureToggle = FeatureToggleImpl(remoteFeatureToggleStorage, localFeatureToggleStorage)

    @Provides
    @Singleton
    fun featureToggle(featureToggle: EditableFeatureToggle): FeatureToggle = featureToggle

    @Provides
    @Singleton
    fun cacheProvider(): CacheProvider = LruCacheProvider()

    @Provides
    @Singleton
    fun cacheCleaner(cacheProvider: CacheProvider): CacheCleaner = cacheProvider
}

@Module
@InstallIn(SingletonComponent::class)
interface BindsModule {

    @Binds
    @Singleton
    fun networkStateProvider(provider: NetworkStateProviderImpl): NetworkStateProvider

    @Binds
    @Singleton
    fun locationProvider(provider: LocationProviderImpl): LocationProvider
}