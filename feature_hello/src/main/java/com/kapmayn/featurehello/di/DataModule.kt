package com.kapmayn.featurehello.di

import com.kapmayn.di.scope.FeatureScope
import com.kapmayn.featurehello.data.api.StubApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class DataModule {

    @Provides
    @FeatureScope
    fun provideStubApi(retrofit: Retrofit): StubApi {
        return retrofit.create(StubApi::class.java)
    }
}