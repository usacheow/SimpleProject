package com.kapmayn.featuredagger.di

import com.kapmayn.core.mediator.HelloMediator
import com.kapmayn.di.ApplicationScope
import com.kapmayn.featurehello.presentation.router.HelloMediatorImpl
import dagger.Binds
import dagger.Module

@Module
abstract class MediatorModule {

    @Binds
    @ApplicationScope
    abstract fun provideHelloMediator(api: HelloMediatorImpl): HelloMediator
}