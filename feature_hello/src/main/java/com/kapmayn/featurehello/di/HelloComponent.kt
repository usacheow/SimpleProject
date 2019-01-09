package com.kapmayn.featurehello.di

import com.kapmayn.core.di.CoreProvider
import com.kapmayn.corefeature.di.FeatureApiProvider
import com.kapmayn.di.BaseProvider
import com.kapmayn.di.scope.FeatureScope
import com.kapmayn.domain.di.DomainComponent
import com.kapmayn.domain.di.DomainProvider
import com.kapmayn.featurehello.activity.HelloActivity
import com.kapmayn.viewmodels.di.ViewModelModule
import dagger.Component

@FeatureScope
@Component(
        dependencies = [FeatureApiProvider::class, DomainProvider::class],
    modules = [HelloModule::class, ViewModelModule::class]
)
interface HelloComponent {

    companion object {

        fun init(coreProvider: CoreProvider): HelloComponent {
            val domainProvider = DomainComponent.init(coreProvider as BaseProvider)
            val featureApiProvider = coreProvider as FeatureApiProvider

            return DaggerHelloComponent
                .builder()
                    .featureApiProvider(featureApiProvider)
                .domainProvider(domainProvider)
                .build()
        }
    }

    fun inject(activity: HelloActivity)
}