package com.kapmayn.featurehello.di

import com.kapmayn.coreuikit.viewmodels.ViewModelModule
import com.kapmayn.di.scope.FeatureScope
import com.kapmayn.diproviders.provider.DiProvider
import com.kapmayn.featurehello.presentation.activity.HelloActivity
import dagger.Component

@FeatureScope
@Component(
    dependencies = [DiProvider::class],
    modules = [PresentationModule::class, DataModule::class, ViewModelModule::class]
)
interface HelloComponent {

    companion object {

        fun init(diProvider: DiProvider): HelloComponent {
            return DaggerHelloComponent
                .builder()
                .diProvider(diProvider)
                .build()
        }
    }

    fun inject(activity: HelloActivity)
}