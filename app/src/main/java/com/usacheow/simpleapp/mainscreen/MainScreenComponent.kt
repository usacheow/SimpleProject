package com.usacheow.simpleapp.mainscreen

import com.usacheow.di.FeatureScope
import com.usacheow.diprovider.DiProvider
import dagger.Component

@FeatureScope
@Component(
    dependencies = [DiProvider::class],
    modules = [MainScreenModule::class]
)
interface MainScreenComponent {

    companion object {

        fun init(diProvider: DiProvider): MainScreenComponent {
            return DaggerMainScreenComponent.builder()
                .diProvider(diProvider)
                .build()
        }
    }

    fun inject(activity: MainScreenActivity)
}