package com.usacheow.featurehello.di

import com.usacheow.di.FragmentScope
import com.usacheow.diprovider.DiProvider
import com.usacheow.featurehello.presentation.activity.HelloActivity
import com.usacheow.featurehello.presentation.fragment.AFragment
import com.usacheow.featurehello.presentation.fragment.BFragment
import dagger.Component

@FragmentScope
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

    fun inject(fragment: AFragment)

    fun inject(fragment: BFragment)
}