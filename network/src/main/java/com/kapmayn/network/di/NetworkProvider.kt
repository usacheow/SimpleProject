package com.kapmayn.network.di

import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

interface NetworkProvider {

    fun provideCompositeDisposable(): CompositeDisposable

    fun provideCompositeSubscription(): CompositeSubscription

    fun provideRetrofit(): Retrofit
}