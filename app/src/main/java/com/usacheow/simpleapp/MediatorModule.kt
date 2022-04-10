package com.usacheow.simpleapp

import com.usacheow.corenavigation.BottomBarFeatureProvider
import com.usacheow.corenavigation.MainFeatureProvider
import com.usacheow.featurebottombar.BottomBarFeatureProviderImpl
import com.usacheow.featuremain.presentation.navigation.MainFeatureProviderImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface MediatorModule {

    @Binds
    @Singleton
    fun bottomBarMediator(mediator: BottomBarFeatureProviderImpl): BottomBarFeatureProvider

    @Binds
    @Singleton
    fun mainMediator(mediator: MainFeatureProviderImpl): MainFeatureProvider
}