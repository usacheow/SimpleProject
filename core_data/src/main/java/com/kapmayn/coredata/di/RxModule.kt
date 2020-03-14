package com.kapmayn.coredata.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

@Module
class RxModule {

    @Provides
    fun provideRxJava2CallAdapterFactory() = RxJava2CallAdapterFactory.create()

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()
}