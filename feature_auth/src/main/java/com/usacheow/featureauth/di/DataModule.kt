package com.usacheow.featureauth.di

import com.usacheow.di.FragmentScope
import com.usacheow.featureauth.data.AuthRepositoryImpl
import com.usacheow.featureauth.domain.AuthRepository
import dagger.Binds
import dagger.Module

@Module
interface DataModule {

    @Binds
    @FragmentScope
    fun provideRepository(repository: AuthRepositoryImpl): AuthRepository
}