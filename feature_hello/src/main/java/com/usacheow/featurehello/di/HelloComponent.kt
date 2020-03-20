package com.usacheow.featurehello.di

import com.usacheow.di.FeatureScope
import com.usacheow.diprovider.DiProvider
import com.usacheow.featurehello.presentation.activity.HelloActivity
import dagger.Component

@FeatureScope
@Component(
    dependencies = [DiProvider::class],
    modules = [PresentationModule::class, DataModule::class]
)
interface HelloComponent {

    companion object {

        fun init(diProvider: DiProvider): HelloComponent {
            return DaggerHelloComponent.builder()
                .diProvider(diProvider)
                .build()
        }
    }

    fun inject(activity: HelloActivity)
}