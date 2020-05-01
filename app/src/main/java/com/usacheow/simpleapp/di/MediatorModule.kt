package com.usacheow.simpleapp.di

import com.usacheow.core.mediator.AuthorizationMediator
import com.usacheow.core.mediator.HelloMediator
import com.usacheow.di.ApplicationScope
import com.usacheow.featureauth.presentation.router.AuthorizationMediatorImpl
import com.usacheow.featurehello.presentation.router.HelloMediatorImpl
import dagger.Binds
import dagger.Module

@Module
interface MediatorModule {

    @Binds
    @ApplicationScope
    fun provideHelloMediator(mediator: HelloMediatorImpl): HelloMediator

    @Binds
    @ApplicationScope
    fun provideAuthorizationMediator(mediator: AuthorizationMediatorImpl): AuthorizationMediator
}