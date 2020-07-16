package com.usacheow.simpleapp.di

import com.usacheow.coremediator.mediator.AuthorizationMediator
import com.usacheow.coremediator.mediator.HelloMediator
import com.usacheow.coremediator.mediator.OnBoardingMediator
import com.usacheow.featureauth.presentation.router.AuthorizationMediatorImpl
import com.usacheow.featurehello.presentation.router.HelloMediatorImpl
import com.usacheow.featureonboarding.OnBoardingMediatorImpl
import dagger.Binds
import dagger.Module
import dagger.Reusable

@Module
interface MediatorModule {

    @Binds
    @Reusable
    fun helloMediator(mediator: HelloMediatorImpl): HelloMediator

    @Binds
    @Reusable
    fun onBoardingMediator(mediator: OnBoardingMediatorImpl): OnBoardingMediator

    @Binds
    @Reusable
    fun authorizationMediator(mediator: AuthorizationMediatorImpl): AuthorizationMediator
}