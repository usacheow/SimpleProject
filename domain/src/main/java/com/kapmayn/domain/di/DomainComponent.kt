package com.kapmayn.domain.di

import com.kapmayn.core.di.CoreProvider
import com.kapmayn.data.di.DataModule
import com.kapmayn.di.scope.ApplicationScope
import com.kapmayn.network.di.NetworkComponent
import com.kapmayn.network.di.NetworkProvider
import dagger.Component

@ApplicationScope
@Component(
    dependencies = [
        CoreProvider::class,
        NetworkProvider::class
    ],
    modules = [
        DomainModule::class,
        DataModule::class
    ]
)
interface DomainComponent : DomainProvider {

    companion object {

        fun init(coreProvider: CoreProvider): DomainProvider {
            val networkProvider = NetworkComponent.init()

            return DaggerDomainComponent
                .builder()
                .coreProvider(coreProvider)
                .networkProvider(networkProvider)
                .build()
        }
    }
}