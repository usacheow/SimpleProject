package com.kapmayn.coredata.di

import com.kapmayn.coredata.AppDatabase
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

interface CoreDataProvider {

    fun provideAppDatabase(): AppDatabase

    fun provideCompositeDisposable(): CompositeDisposable

    fun provideRetrofit(): Retrofit
}