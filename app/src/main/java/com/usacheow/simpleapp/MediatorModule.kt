package com.usacheow.simpleapp

import com.usacheow.corenavigation.AuthorizationFeatureProvider
import com.usacheow.corenavigation.BottomBarFeatureProvider
import com.usacheow.corenavigation.MainFeatureProvider
import com.usacheow.corenavigation.OnBoardingFeatureProvider
import com.usacheow.corenavigation.OtpFeatureProvider
import com.usacheow.corenavigation.PurchaseFeatureProvider
import com.usacheow.featureauth.presentation.navigation.AuthorizationFeatureProviderImpl
import com.usacheow.featurebottombar.BottomBarFeatureProviderImpl
import com.usacheow.featuremain.presentation.navigation.MainFeatureProviderImpl
import com.usacheow.featureonboarding.navigation.OnBoardingFeatureProviderImpl
import com.usacheow.featureotp.navigation.OtpFeatureProviderImpl
import com.usacheow.featurepurchase.navigation.PurchaseFeatureProviderImpl
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

    @Binds
    @Singleton
    fun onBoardingMediator(mediator: OnBoardingFeatureProviderImpl): OnBoardingFeatureProvider

    @Binds
    @Singleton
    fun authorizationMediator(mediator: AuthorizationFeatureProviderImpl): AuthorizationFeatureProvider

    @Binds
    @Singleton
    fun purchaseMediator(mediator: PurchaseFeatureProviderImpl): PurchaseFeatureProvider

    @Binds
    @Singleton
    fun otpMediator(mediator: OtpFeatureProviderImpl): OtpFeatureProvider
}