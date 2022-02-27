package com.usacheow.appdemo

import com.usacheow.corenavigation.AuthorizationFeatureProvider
import com.usacheow.corenavigation.OnBoardingFeatureProvider
import com.usacheow.corenavigation.OtpFeatureProvider
import com.usacheow.featureauth.presentation.navigation.AuthorizationFeatureProviderImpl
import com.usacheow.featureonboarding.navigation.OnBoardingFeatureProviderImpl
import com.usacheow.featureotp.navigation.OtpFeatureProviderImpl
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
    fun onBoardingMediator(mediator: OnBoardingFeatureProviderImpl): OnBoardingFeatureProvider

    @Binds
    @Singleton
    fun authorizationMediator(mediator: AuthorizationFeatureProviderImpl): AuthorizationFeatureProvider

    @Binds
    @Singleton
    fun otpMediator(mediator: OtpFeatureProviderImpl): OtpFeatureProvider
}