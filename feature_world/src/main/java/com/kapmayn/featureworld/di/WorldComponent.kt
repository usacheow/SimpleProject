package com.kapmayn.featureworld.di

import com.kapmayn.di.scope.FeatureScope
import com.kapmayn.diproviders.DiProvider
import com.kapmayn.featureworld.presentation.activity.WorldActivity
import dagger.Component

@FeatureScope
@Component(
    dependencies = [DiProvider::class],
    modules = [PresentationModule::class, DataModule::class]
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