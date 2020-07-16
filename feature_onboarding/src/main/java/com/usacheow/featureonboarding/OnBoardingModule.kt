package com.usacheow.featureonboarding

import androidx.lifecycle.ViewModel
import com.usacheow.app_shared.AppStateViewModel
import com.usacheow.coreui.viewmodels.ViewModelFactoryModule
import com.usacheow.coreui.viewmodels.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelFactoryModule::class])
interface OnBoardingModule {

    @Binds
    @IntoMap
    @ViewModelKey(AppStateViewModel::class)
    fun appStateViewModel(viewModel: AppStateViewModel): ViewModel
}