package com.kapmayn.featuredagger.di

import android.app.Application
import android.content.Context
import android.content.res.Resources
import com.kapmayn.di.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class CoreModule(
    private val application: Application
) {

    @Provides
    @ApplicationScope
    fun provideApplication() = application

    @Provides
    @ApplicationScope
    fun provideContext(): Context = application

    @Provides
    @ApplicationScope
    fun provideResources(): Resources = application.resources
}