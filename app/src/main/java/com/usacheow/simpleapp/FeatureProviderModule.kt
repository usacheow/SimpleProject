package com.usacheow.simpleapp

import com.usacheow.corenavigation.BottomBarFeatureProvider
import com.usacheow.corenavigation.MainFeatureProvider
import com.usacheow.corenavigation.OnBoardingFeatureProvider
import com.usacheow.featurebottombar.BottomBarFeatureProviderImpl
import com.usacheow.featuremain.presentation.MainFeatureProviderImpl
import com.usacheow.featureonboarding.presentation.OnBoardingFeatureProviderImpl
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
    fun bottomBarProvider(provider: BottomBarFeatureProviderImpl): BottomBarFeatureProvider

    @Binds
    @Singleton
    fun mainProvider(provider: MainFeatureProviderImpl): MainFeatureProvider

    @Binds
    @Singleton
    fun onBoardingProvider(provider: OnBoardingFeatureProviderImpl): OnBoardingFeatureProvider
}