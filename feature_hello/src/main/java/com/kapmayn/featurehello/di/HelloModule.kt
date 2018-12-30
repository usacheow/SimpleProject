package com.kapmayn.featurehello.di

import androidx.lifecycle.ViewModelProvider
import com.kapmayn.di.scope.FeatureScope
import com.kapmayn.featurehello.router.HelloRouter
import com.kapmayn.featurehello.router.HelloRouterImpl
import com.kapmayn.viewmodels.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class HelloModule {

    @Binds
    @FeatureScope
    abstract fun bindRouter(router: HelloRouterImpl): HelloRouter

    @Binds
    @FeatureScope
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}