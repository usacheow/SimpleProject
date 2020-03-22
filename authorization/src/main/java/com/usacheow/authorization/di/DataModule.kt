package com.usacheow.authorization.di

import com.usacheow.authorization.data.AuthRepository
import com.usacheow.authorization.data.AuthRepositoryImpl
import com.usacheow.di.FeatureScope
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    @FeatureScope
    fun provideRepository(): AuthRepository = AuthRepositoryImpl()
}