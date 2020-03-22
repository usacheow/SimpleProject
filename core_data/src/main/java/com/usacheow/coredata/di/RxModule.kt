package com.usacheow.coredata.di

import com.google.gson.Gson
import com.usacheow.coredata.network.error.RxErrorHandlingCallAdapterFactory
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

@Module
class RxModule {

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideRxJava2CallAdapterFactory() = RxJava2CallAdapterFactory.create()

    @Provides
    fun provideRetailsRxCallFactory(gson: Gson) = RxErrorHandlingCallAdapterFactory(
        RxJava2CallAdapterFactory.create(),
        gson
    )
}