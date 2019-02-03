package com.kapmayn.featurehello.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kapmayn.core.presentation.viewmodels.ViewModelFactory
import com.kapmayn.core.presentation.viewmodels.ViewModelKey
import com.kapmayn.di.scope.FeatureScope
import com.kapmayn.featurehello.presentation.router.HelloFeatureRouter
import com.kapmayn.featurehello.presentation.router.HelloFeatureRouterImpl
import com.kapmayn.featurehello.presentation.viewmodels.HelloViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PresentationModule {

    @Binds
    @FeatureScope
    abstract fun bindRouter(router: HelloFeatureRouterImpl): HelloFeatureRouter

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HelloViewModel::class)
    abstract fun helloViewModel(viewModel: HelloViewModel): ViewModel
}