package com.usacheow.coreuikit.viewmodels

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
interface ViewModelFactoryModule {

    @Binds
    fun bindsFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}