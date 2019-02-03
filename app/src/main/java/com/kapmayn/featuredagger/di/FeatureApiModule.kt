package com.kapmayn.featuredagger.di

import com.kapmayn.di.scope.ApplicationScope
import com.kapmayn.diproviders.featureapi.HelloFeatureApi
import com.kapmayn.diproviders.featureapi.WorldFeatureApi
import com.kapmayn.featurehello.di.HelloFeatureApiImpl
import com.kapmayn.featureworld.di.WorldFeatureApiImpl
import dagger.Binds
import dagger.Module

@Module
abstract class FeatureApiModule {

    @Binds
    @ApplicationScope
    abstract fun provideHelloFeatureApi(api: HelloFeatureApiImpl): HelloFeatureApi

    @Binds
    @ApplicationScope
    abstract fun provideWorldFeatureApi(api: WorldFeatureApiImpl): WorldFeatureApi
}