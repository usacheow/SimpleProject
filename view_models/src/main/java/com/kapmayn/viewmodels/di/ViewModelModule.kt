package com.kapmayn.viewmodels.di

import androidx.lifecycle.ViewModel
import com.kapmayn.viewmodels.HelloViewModel
import com.kapmayn.viewmodels.WorldViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(HelloViewModel::class)
    fun helloViewModel(viewModel: HelloViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(WorldViewModel::class)
    fun worldViewModel(viewModel: WorldViewModel): ViewModel
}