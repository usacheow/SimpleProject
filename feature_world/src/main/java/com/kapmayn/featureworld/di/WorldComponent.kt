package com.kapmayn.featureworld.di

import com.kapmayn.coreuikit.viewmodels.ViewModelModule
import com.kapmayn.di.scope.FeatureScope
import com.kapmayn.diproviders.provider.DiProvider
import com.kapmayn.featureworld.presentation.activity.WorldActivity
import dagger.Component

@FeatureScope
@Component(
    dependencies = [DiProvider::class],
    modules = [PresentationModule::class, DataModule::class, ViewModelModule::class]
)
interface WorldComponent {

    companion object {

        fun init(diProvider: DiProvider): WorldComponent {
            return DaggerWorldComponent
                .builder()
                .diProvider(diProvider)
                .build()
        }
    }

    fun inject(activity: WorldActivity)
}