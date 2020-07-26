package com.usacheow.simpleapp

import com.usacheow.coremediator.AuthorizationMediator
import com.usacheow.coremediator.HelloMediator
import com.usacheow.coremediator.OnBoardingMediator
import com.usacheow.featureauth.presentation.router.AuthorizationMediatorImpl
import com.usacheow.featurehello.presentation.router.HelloMediatorImpl
import com.usacheow.featureonboarding.OnBoardingMediatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
interface MediatorModule {

    @Binds
    @Singleton
    fun helloMediator(mediator: HelloMediatorImpl): HelloMediator

    @Binds
    @Singleton
    fun onBoardingMediator(mediator: OnBoardingMediatorImpl): OnBoardingMediator

    @Binds
    @Singleton
    fun authorizationMediator(mediator: AuthorizationMediatorImpl): AuthorizationMediator
}