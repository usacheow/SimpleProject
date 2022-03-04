package com.usacheow.coredata.database

import android.content.Context
import com.usacheow.coredata.cache.CacheCleaner
import com.usacheow.coredata.cache.CacheProvider
import com.usacheow.coredata.cache.LruCacheProvider
import com.usacheow.coredata.coroutine.CoroutineModule
import com.usacheow.coredata.database.AppDatabase
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

@Module
@InstallIn(SingletonComponent::class)
class AppDatabaseModule {

    @Provides
    @Singleton
    fun appDatabase(@ApplicationContext context: Context): AppDatabase = AppDatabase.newInstance(context)
}