package com.kapmayn.featureworld.di

import com.kapmayn.core.di.CoreProvider
import com.kapmayn.corefeature.di.FeatureApiProvider
import com.kapmayn.di.BaseProvider
import com.kapmayn.di.scope.FeatureScope
import com.kapmayn.domain.di.DomainComponent
import com.kapmayn.domain.di.DomainProvider
import com.kapmayn.featureworld.activity.WorldActivity
import com.kapmayn.viewmodels.di.ViewModelModule
import dagger.Component

@FeatureScope
@Component(
    dependencies = [FeatureApiProvider::class, DomainProvider::class],
    modules = [WorldModule::class, ViewModelModule::class]
)
interface WorldComponent {

    companion object {

        fun init(coreProvider: CoreProvider): WorldComponent {
            val domainProvider = DomainComponent.init(coreProvider as BaseProvider)
            val featureApiProvider = coreProvider as FeatureApiProvider

            return DaggerWorldComponent
                .builder()
                .featureApiProvider(featureApiProvider)
                .domainProvider(domainProvider)
                .build()
        }
    }

    fun inject(activity: WorldActivity)
}