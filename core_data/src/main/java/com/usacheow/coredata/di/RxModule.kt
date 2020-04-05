package com.usacheow.coredata.di

import com.google.gson.Gson
import com.usacheow.coredata.network.error.RxErrorHandlingCallAdapterFactory
import com.usacheow.di.ApplicationScope
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

@Module
class RxModule {

    @Provides
    @ApplicationScope
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @ApplicationScope
    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    @Provides
    @ApplicationScope
    fun provideRetailsRxCallFactory(factory: RxJava2CallAdapterFactory, gson: Gson) = RxErrorHandlingCallAdapterFactory(factory, gson)
}