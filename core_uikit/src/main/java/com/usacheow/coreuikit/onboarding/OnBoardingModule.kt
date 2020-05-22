package com.usacheow.coreuikit.onboarding

import androidx.lifecycle.ViewModel
import com.usacheow.coreuikit.AppStateViewModel
import com.usacheow.coreuikit.viewmodels.ViewModelFactoryModule
import com.usacheow.coreuikit.viewmodels.ViewModelKey
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