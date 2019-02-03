package com.kapmayn.datanetwork.di

import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

interface NetworkProvider {

    fun provideCompositeDisposable(): CompositeDisposable

    fun provideRetrofit(): Retrofit
}