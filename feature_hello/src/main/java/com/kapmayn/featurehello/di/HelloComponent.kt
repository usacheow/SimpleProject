package com.kapmayn.featurehello.di

import com.kapmayn.core.di.CoreProvider
import com.kapmayn.core.di.scope.FeatureScope
import com.kapmayn.domain.di.DomainComponent
import com.kapmayn.domain.di.DomainProvider
import com.kapmayn.featurehello.activity.HelloActivity
import com.kapmayn.viewmodels.di.ViewModelModule
import dagger.Component

@FeatureScope
@Component(
    dependencies = [DomainProvider::class],
    modules = [HelloModule::class, ViewModelModule::class]
)
interface HelloComponent {

    companion object {

        fun init(coreProvider: CoreProvider): HelloComponent {
            val domainProvider = DomainComponent.init(coreProvider)

            return DaggerHelloComponent
                .builder()
                .domainProvider(domainProvider)
                .build()
        }
    }

    fun inject(activity: HelloActivity)
}