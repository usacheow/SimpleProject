package com.kapmayn.domain.di

import com.kapmayn.data.di.DataModule
import com.kapmayn.di.BaseProvider
import com.kapmayn.di.scope.ApplicationScope
import com.kapmayn.network.di.NetworkComponent
import com.kapmayn.network.di.NetworkProvider
import dagger.Component

@ApplicationScope
@Component(
    dependencies = [
        BaseProvider::class,
        NetworkProvider::class
    ],
    modules = [
        DomainModule::class,
        DataModule::class
    ]
)
interface DomainComponent : DomainProvider {

    companion object {

        fun init(baseProvider: BaseProvider): DomainProvider {
            val networkProvider = NetworkComponent.init()

            return DaggerDomainComponent
                .builder()
                .baseProvider(baseProvider)
                .networkProvider(networkProvider)
                .build()
        }
    }
}