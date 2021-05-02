package com.usacheow.coreui

import android.app.Application
import com.usacheow.coreui.app.ApplicationCoroutineScopeHolder
import com.usacheow.coreui.di.ApplicationCoroutineScope
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope

@Module
@InstallIn(SingletonComponent::class)
class CoroutineModule {

    @Provides
    @ApplicationCoroutineScope
    fun scope(application: Application): CoroutineScope = (application as ApplicationCoroutineScopeHolder).applicationScope
}