package com.usacheow.featureauth.di

import com.usacheow.di.FragmentScope
import com.usacheow.featureauth.data.AuthRepository
import com.usacheow.featureauth.data.AuthRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    @FragmentScope
    fun provideRepository(): AuthRepository = AuthRepositoryImpl()
}