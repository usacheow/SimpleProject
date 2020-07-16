package com.usacheow.coredata.di

import android.content.Context
import androidx.room.Room
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
import dagger.Reusable

@Module(
    includes = [
        OkHttpModule::class,
        RxModule::class,
        GsonModule::class,
        RetrofitModule::class
    ]
)
class CoreDataModule {

    @Provides
    @Reusable
    fun appDatabase(context: Context): AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        AppDatabase.DATABASE_NAME
    ).fallbackToDestructiveMigration()
        .build()

    @Provides
    @Reusable
    fun editableFeatureToggle(featureToggleStorage: FeatureToggleStorage): EditableFeatureToggle = FeatureToggleImpl(featureToggleStorage)

    @Provides
    @Reusable
    fun featureToggle(featureToggle: EditableFeatureToggle): FeatureToggle = featureToggle

    @Provides
    @Reusable
    fun cacheProvider(): CacheProvider = LruCacheProvider()

    @Provides
    @Reusable
    fun cacheCleaner(cacheProvider: CacheProvider): CacheCleaner = cacheProvider
}