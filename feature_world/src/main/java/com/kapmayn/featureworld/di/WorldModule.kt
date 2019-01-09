package com.kapmayn.featureworld.di

import androidx.lifecycle.ViewModelProvider
import com.kapmayn.di.scope.FeatureScope
import com.kapmayn.featureworld.router.WorldFeatureRouter
import com.kapmayn.featureworld.router.WorldFeatureRouterImpl
import com.kapmayn.viewmodels.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class WorldModule {

    @Binds
    @FeatureScope
    abstract fun bindRouter(router: WorldFeatureRouterImpl): WorldFeatureRouter

    @Binds
    @FeatureScope
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}