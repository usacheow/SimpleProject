package com.usacheow.coredata.di

import com.google.gson.Gson
import com.usacheow.coredata.network.error.RxErrorHandlingCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class RxModule {

    @Provides
    @Singleton
    fun rxJava2CallAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    @Provides
    @Singleton
    fun rxCallFactory(factory: RxJava2CallAdapterFactory, gson: Gson) = RxErrorHandlingCallAdapterFactory(factory, gson)
}