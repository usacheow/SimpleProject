package com.usacheow.simpleapp

import com.usacheow.corenavigation.ExampleFeatureProvider
import com.usacheow.featureexample.presentation.ExampleFeatureProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface FeatureProviderModule {

    @Binds
    @Singleton
    fun exampleProvider(provider: ExampleFeatureProviderImpl): ExampleFeatureProvider
}