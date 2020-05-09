package com.usacheow.simpleapp.mainscreen.di

import androidx.lifecycle.ViewModel
import com.usacheow.coreuikit.AppStateViewModel
import com.usacheow.coreuikit.viewmodels.ViewModelFactoryModule
import com.usacheow.coreuikit.viewmodels.ViewModelKey
import com.usacheow.simpleapp.mainscreen.BottomBarViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelFactoryModule::class])
interface MainScreenModule {

    @Binds
    @IntoMap
    @ViewModelKey(BottomBarViewModel::class)
    fun bottomBarViewModel(viewModel: BottomBarViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AppStateViewModel::class)
    fun appStateViewModel(viewModel: AppStateViewModel): ViewModel
}