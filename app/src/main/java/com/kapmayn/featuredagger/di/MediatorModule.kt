package com.kapmayn.featuredagger.di

import com.kapmayn.di.scope.ApplicationScope
import com.kapmayn.diproviders.mediator.HelloMediator
import com.kapmayn.diproviders.mediator.WorldMediator
import com.kapmayn.featurehello.HelloMediatorImpl
import com.kapmayn.featureworld.WorldMediatorImpl
import dagger.Binds
import dagger.Module

@Module
abstract class MediatorModule {

    @Binds
    @ApplicationScope
    abstract fun provideHelloMediator(api: HelloMediatorImpl): HelloMediator

    @Binds
    @ApplicationScope
    abstract fun provideWorldMediator(api: WorldMediatorImpl): WorldMediator
}