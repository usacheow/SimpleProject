package com.usacheow.simpleapp.mainscreen.di

import androidx.lifecycle.ViewModel
import com.usacheow.coreuikit.viewmodels.ViewModelFactoryModule
import com.usacheow.coreuikit.viewmodels.ViewModelKey
import com.usacheow.simpleapp.mainscreen.StateViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelFactoryModule::class])
interface MainScreenModule {

    @Binds
    @IntoMap
    @ViewModelKey(StateViewModel::class)
    fun appViewModel(viewModel: StateViewModel): ViewModel
}