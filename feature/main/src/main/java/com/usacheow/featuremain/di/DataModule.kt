package com.usacheow.featuremain.di

import com.usacheow.coredata.network.ApiConfig
import com.usacheow.coredata.network.ApiService
import com.usacheow.featuremain.data.StubApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DataModule {

    @Provides
    @ViewModelScoped
    fun stubApi(config: ApiConfig) = config.serviceBuilder(StubApi::class.java)
        .service(ApiService.Stub)
        .interceptor(config.authentication)
        .build()
}