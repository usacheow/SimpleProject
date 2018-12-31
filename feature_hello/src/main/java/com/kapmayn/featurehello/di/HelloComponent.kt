package com.kapmayn.featurehello.di

import com.kapmayn.di.BaseProvider
import com.kapmayn.di.scope.FeatureScope
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

        fun init(baseProvider: BaseProvider): HelloComponent {
            val domainProvider = DomainComponent.init(baseProvider)

            return DaggerHelloComponent
                .builder()
                .domainProvider(domainProvider)
                .build()
        }
    }

    fun inject(activity: HelloActivity)
}