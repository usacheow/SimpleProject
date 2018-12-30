package com.kapmayn.network.di

import com.kapmayn.core.di.scope.ApplicationScope
import dagger.Component

@ApplicationScope
@Component(
    modules = [
        OkHttpModule::class,
        RxModule::class,
        GsonModule::class,
        RetrofitModule::class
    ]
)
interface NetworkComponent : NetworkProvider {

    companion object {

        fun init(): NetworkProvider {
            return DaggerNetworkComponent.builder().build()
        }
    }
}