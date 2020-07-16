package com.usacheow.featureonboarding

import com.usacheow.di.FragmentScope
import com.usacheow.diprovider.DiProvider
import dagger.Component

@FragmentScope
@Component(
    dependencies = [DiProvider::class],
    modules = [OnBoardingModule::class]
)
interface OnBoardingComponent {

    companion object {

        fun init(diProvider: DiProvider): OnBoardingComponent {
            return DaggerOnBoardingComponent.builder()
                .diProvider(diProvider)
                .build()
        }
    }

    fun inject(fragment: OnBoardingFragment)
}