package com.kapmayn.featuredagger.di

import com.kapmayn.di.scope.ApplicationScope
import com.kapmayn.diproviders.mediator.HelloMediator
import com.kapmayn.diproviders.mediator.WorldMediator
import com.kapmayn.featurehello.di.HelloMediatorImpl
import com.kapmayn.featureworld.di.WorldMediatorImpl
import dagger.Binds
import dagger.Module

@Module
abstract class FeatureApiModule {

    @Binds
    @ApplicationScope
    abstract fun provideHelloFeatureApi(api: HelloMediatorImpl): HelloMediator

    @Binds
    @ApplicationScope
    abstract fun provideWorldFeatureApi(api: WorldMediatorImpl): WorldMediator
}