package com.usacheow.featureauth.di

import com.usacheow.featureauth.data.AuthRepository
import com.usacheow.featureauth.data.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface DataModule {

    @Binds
    @ViewModelScoped
    fun provideRepository(repository: AuthRepositoryImpl): AuthRepository
}