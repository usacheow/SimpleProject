package com.kapmayn.coreuikit.viewmodels

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindsFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}