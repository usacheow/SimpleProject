package com.usacheow.coreuikit.viewmodels

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindsFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}