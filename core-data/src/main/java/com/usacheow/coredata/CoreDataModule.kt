package com.usacheow.coredata

import android.content.Context
import com.usacheow.coredata.cache.CacheCleaner
import com.usacheow.coredata.cache.CacheProvider
import com.usacheow.coredata.cache.lruimpl.LruCacheProvider
import com.usacheow.coredata.database.AppDatabase
import com.usacheow.coredata.featuretoggle.EditableFeatureToggle
import com.usacheow.coredata.featuretoggle.FeatureToggle
import com.usacheow.coredata.featuretoggle.FeatureToggleImpl
import com.usacheow.coredata.featuretoggle.ManualFeatureToggleStorage
import com.usacheow.coredata.featuretoggle.RemoteFeatureToggleStorage
import com.usacheow.coredata.location.LocationProvider
import com.usacheow.coredata.location.LocationProviderImpl
import com.usacheow.coredata.network.NetworkStateProvider
import com.usacheow.coredata.network.NetworkStateProviderImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(
    includes = [
        OkHttpModule::class,
        GsonModule::class,
        RetrofitModule::class,
        CoroutineModule::class,
        BindsModule::class,
    ]
)
@InstallIn(SingletonComponent::class)
class CoreDataModule {

    @Provides
    @Singleton
    fun appDatabase(context: Context): AppDatabase = AppDatabase.newInstance(context.applicationContext)

    @Provides
    @Singleton
    fun editableFeatureToggle(
        remoteFeatureToggleStorage: RemoteFeatureToggleStorage,
        manualFeatureToggleStorage: ManualFeatureToggleStorage,
    ): EditableFeatureToggle = FeatureToggleImpl(remoteFeatureToggleStorage, manualFeatureToggleStorage)

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