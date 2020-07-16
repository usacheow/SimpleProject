package com.usacheow.simpleapp.mainscreen.di

import androidx.lifecycle.ViewModel
import com.usacheow.app_shared.AppStateViewModel
import com.usacheow.coreui.viewmodels.ViewModelFactoryModule
import com.usacheow.coreui.viewmodels.ViewModelKey
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