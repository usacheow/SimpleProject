package com.kapmayn.featureworld.di

import androidx.lifecycle.ViewModel
import com.kapmayn.coreuikit.viewmodels.ViewModelKey
import com.kapmayn.featureworld.presentation.viewmodels.WorldViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(WorldViewModel::class)
    abstract fun worldViewModel(viewModel: WorldViewModel): ViewModel
}