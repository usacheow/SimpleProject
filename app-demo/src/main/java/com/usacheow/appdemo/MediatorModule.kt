package com.usacheow.appdemo

import com.usacheow.coremediator.AuthorizationMediator
import com.usacheow.coremediator.OnBoardingMediator
import com.usacheow.coremediator.OtpMediator
import com.usacheow.featureauth.presentation.navigation.AuthorizationMediatorImpl
import com.usacheow.featureonboarding.navigation.OnBoardingMediatorImpl
import com.usacheow.featureotp.navigation.OtpMediatorImpl
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
    fun onBoardingMediator(mediator: OnBoardingMediatorImpl): OnBoardingMediator

    @Binds
    @Singleton
    fun authorizationMediator(mediator: AuthorizationMediatorImpl): AuthorizationMediator

    @Binds
    @Singleton
    fun otpMediator(mediator: OtpMediatorImpl): OtpMediator
}