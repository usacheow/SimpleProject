package com.usacheow.coremediator

import com.usacheow.coremediator.mediator.AuthorizationMediator
import com.usacheow.coremediator.mediator.HelloMediator
import com.usacheow.coremediator.mediator.OnBoardingMediator

interface MediatorProvider {

    fun provideHelloMediator(): HelloMediator

    fun provideOnBoardingMediator(): OnBoardingMediator

    fun provideAuthorizationMediator(): AuthorizationMediator
}