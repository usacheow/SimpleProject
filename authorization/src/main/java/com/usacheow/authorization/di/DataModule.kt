package com.usacheow.authorization.di

import com.usacheow.authorization.data.AuthRepository
import com.usacheow.authorization.data.AuthRepositoryImpl
import com.usacheow.di.FragmentScope
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    @FragmentScope
    fun provideRepository(): AuthRepository = AuthRepositoryImpl()
}