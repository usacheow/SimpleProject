package com.usacheow.simpleapp.di

import com.usacheow.authorization.presentation.router.AuthorizationMediatorImpl
import com.usacheow.core.mediator.AuthorizationMediator
import com.usacheow.core.mediator.HelloMediator
import com.usacheow.di.ApplicationScope
import com.usacheow.featurehello.presentation.router.HelloMediatorImpl
import dagger.Binds
import dagger.Module

@Module
abstract class MediatorModule {

    @Binds
    @ApplicationScope
    abstract fun provideHelloMediator(mediator: HelloMediatorImpl): HelloMediator

    @Binds
    @ApplicationScope
    abstract fun provideAuthorizationMediator(mediator: AuthorizationMediatorImpl): AuthorizationMediator
}