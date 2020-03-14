package com.kapmayn.featuredagger.di

import com.kapmayn.di.scope.ApplicationScope
import com.kapmayn.diproviders.mediator.HelloMediator
import com.kapmayn.featurehello.presentation.router.HelloMediatorImpl
import dagger.Binds
import dagger.Module

@Module
abstract class MediatorModule {

    @Binds
    @ApplicationScope
    abstract fun provideHelloMediator(api: HelloMediatorImpl): HelloMediator
}