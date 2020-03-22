package com.usacheow.diprovider

import com.usacheow.coredata.database.AppDatabase
import com.usacheow.coredata.featuretoggle.FeatureToggle
import io.reactivex.disposables.CompositeDisposable
import retrofit2.Retrofit

interface CoreDataProvider {

    fun provideAppDatabase(): AppDatabase

    fun provideCompositeDisposable(): CompositeDisposable

    fun provideRetrofit(): Retrofit

    fun provideFeatureToggle(): FeatureToggle
}