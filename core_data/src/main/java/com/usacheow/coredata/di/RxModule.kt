package com.usacheow.coredata.di

import com.google.gson.Gson
import com.usacheow.coredata.network.error.RxErrorHandlingCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

@Module
class RxModule {

    @Provides
    @Reusable
    fun rxJava2CallAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    @Provides
    @Reusable
    fun rxCallFactory(factory: RxJava2CallAdapterFactory, gson: Gson) = RxErrorHandlingCallAdapterFactory(factory, gson)
}