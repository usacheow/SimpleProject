package com.usacheow.coredata

import com.usacheow.coredata.cache.base.CacheCleaner
import com.usacheow.coredata.cache.base.CacheProvider
import com.usacheow.coredata.database.AppDatabase
import com.usacheow.coredata.featuretoggle.EditableFeatureToggle
import com.usacheow.coredata.featuretoggle.FeatureToggle
import retrofit2.Retrofit

interface CoreDataProvider {

    fun provideAppDatabase(): AppDatabase

    fun provideRetrofit(): Retrofit

    fun provideFeatureToggle(): FeatureToggle

    fun provideEditableFeatureToggle(): EditableFeatureToggle

    fun provideCacheProvider(): CacheProvider

    fun provideCacheCleaner(): CacheCleaner
}