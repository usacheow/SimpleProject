package com.usacheow.coreuiview.resourcewrapper

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ResourcesWrapperModule {

    @Binds
    @Singleton
    fun resources(resources: ResourcesWrapperImpl): ResourcesWrapper
}