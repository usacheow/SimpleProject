package com.usacheow.coredata

import android.content.Context
import com.usacheow.coredata.cache.base.CacheCleaner
import com.usacheow.coredata.cache.base.CacheProvider
import com.usacheow.coredata.cache.lrucache.LruCacheProvider
import com.usacheow.coredata.database.AppDatabase
import com.usacheow.coredata.featuretoggle.EditableFeatureToggle
import com.usacheow.coredata.featuretoggle.FeatureToggle
import com.usacheow.coredata.featuretoggle.FeatureToggleImpl
import com.usacheow.coredata.featuretoggle.FeatureToggleStorage
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
        featureToggleStorage: FeatureToggleStorage,
    ): EditableFeatureToggle = FeatureToggleImpl(featureToggleStorage)

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