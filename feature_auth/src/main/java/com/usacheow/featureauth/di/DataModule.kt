package com.usacheow.featureauth.di

import com.usacheow.featureauth.data.AuthRepository
import com.usacheow.featureauth.data.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped

@Module
@InstallIn(ActivityRetainedComponent::class)
interface DataModule {

    @Binds
    @ActivityRetainedScoped
    fun provideRepository(repository: AuthRepositoryImpl): AuthRepository
}