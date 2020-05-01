package com.usacheow.simpleapp.mainscreen.di

import com.usacheow.di.ActivityScope
import com.usacheow.diprovider.DiProvider
import com.usacheow.simpleapp.mainscreen.BottomBarMainScreenActivity
import dagger.Component

@ActivityScope
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

    fun inject(activity: BottomBarMainScreenActivity)
}