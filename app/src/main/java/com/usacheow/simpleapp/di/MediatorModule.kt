package com.usacheow.simpleapp.di

import com.usacheow.coremediator.mediator.AuthorizationMediator
import com.usacheow.coremediator.mediator.HelloMediator
import com.usacheow.coremediator.mediator.OnBoardingMediator
import com.usacheow.di.ApplicationScope
import com.usacheow.featureauth.presentation.router.AuthorizationMediatorImpl
import com.usacheow.featurehello.presentation.router.HelloMediatorImpl
import com.usacheow.featureonboarding.OnBoardingMediatorImpl
import dagger.Binds
import dagger.Module

@Module
interface MediatorModule {

    @Binds
    @ApplicationScope
    fun provideHelloMediator(mediator: HelloMediatorImpl): HelloMediator

    @Binds
    @ApplicationScope
    fun provideOnBoardingMediator(mediator: OnBoardingMediatorImpl): OnBoardingMediator

    @Binds
    @ApplicationScope
    fun provideAuthorizationMediator(mediator: AuthorizationMediatorImpl): AuthorizationMediator
}