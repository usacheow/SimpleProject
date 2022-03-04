package com.usacheow.coredata.cache

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CacheModule {

    @Provides
    @Singleton
    fun cacheProvider(): CacheProvider = LruCacheProvider()

    @Provides
    @Singleton
    fun cacheCleaner(cacheProvider: CacheProvider): CacheCleaner = cacheProvider
}