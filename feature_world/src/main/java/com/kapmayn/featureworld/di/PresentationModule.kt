package com.kapmayn.featureworld.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kapmayn.core.presentation.viewmodels.ViewModelFactory
import com.kapmayn.core.presentation.viewmodels.ViewModelKey
import com.kapmayn.di.scope.FeatureScope
import com.kapmayn.featureworld.presentation.router.WorldFeatureRouter
import com.kapmayn.featureworld.presentation.router.WorldFeatureRouterImpl
import com.kapmayn.featureworld.presentation.viewmodels.WorldViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PresentationModule {

    @Binds
    @FeatureScope
    abstract fun bindRouter(router: WorldFeatureRouterImpl): WorldFeatureRouter

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(WorldViewModel::class)
    abstract fun worldViewModel(viewModel: WorldViewModel): ViewModel
}