package com.kapmayn.network.di

import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import org.reactivestreams.Subscription
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

typealias CompositeSubscription = MutableList<Subscription>

@Module
class RxModule {

    @Provides
    fun provideRxJava2CallAdapterFactory() = RxJava2CallAdapterFactory.create()

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    fun provideCompositeSubscription(): CompositeSubscription = mutableListOf()
}