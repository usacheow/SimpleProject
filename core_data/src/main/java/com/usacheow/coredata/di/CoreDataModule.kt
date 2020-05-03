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
import com.usacheow.di.ApplicationScope
import dagger.Module
import dagger.Provides

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
    @ApplicationScope
    fun provideAppDatabase(context: Context): AppDatabase = Room.databaseBuilder(
        context.applicationContext,
        AppDatabase::class.java,
        AppDatabase.DATABASE_NAME
    ).fallbackToDestructiveMigration()
        .build()

    @Provides
    @ApplicationScope
    fun provideEditableFeatureToggle(featureToggleStorage: FeatureToggleStorage): EditableFeatureToggle = FeatureToggleImpl(featureToggleStorage)

    @Provides
    @ApplicationScope
    fun provideFeatureToggle(featureToggle: EditableFeatureToggle): FeatureToggle = featureToggle

    @Provides
    @ApplicationScope
    fun provideCacheProvider(): CacheProvider = LruCacheProvider()

    @Provides
    @ApplicationScope
    fun provideCacheCleaner(cacheProvider: CacheProvider): CacheCleaner = cacheProvider
}