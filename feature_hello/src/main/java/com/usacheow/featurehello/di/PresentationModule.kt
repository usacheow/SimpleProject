package com.usacheow.featurehello.di

import androidx.lifecycle.ViewModel
import com.usacheow.coreuikit.viewmodels.ViewModelFactoryModule
import com.usacheow.coreuikit.viewmodels.ViewModelKey
import com.usacheow.di.FragmentScope
import com.usacheow.featurehello.presentation.viewmodels.AViewModel
import com.usacheow.featurehello.presentation.viewmodels.BViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelFactoryModule::class])
interface PresentationModule {

    @Binds
    @IntoMap
    @FragmentScope
    @ViewModelKey(AViewModel::class)
    fun aViewModel(viewModel: AViewModel): ViewModel

    @Binds
    @IntoMap
    @FragmentScope
    @ViewModelKey(BViewModel::class)
    fun bViewModel(viewModel: BViewModel): ViewModel
}