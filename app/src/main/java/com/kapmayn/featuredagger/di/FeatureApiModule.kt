package com.kapmayn.featuredagger.di

import com.kapmayn.corefeature.HelloFeatureApi
import com.kapmayn.corefeature.WorldFeatureApi
import com.kapmayn.di.scope.ApplicationScope
import com.kapmayn.featurehello.featureapi.HelloFeatureApiImpl
import com.kapmayn.featureworld.featureapi.WorldFeatureApiImpl
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