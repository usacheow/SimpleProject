package com.usacheow.basesources

import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val baseSourcesDiModule by DI.Module {
    bindSingleton<DeviceInfoProvider> { DeviceInfoProviderImpl() }
    bindSingleton { LocaleSource(instance(), instance()) }
    bindSingleton<LocationSource> { LocationSourceImpl(instance()) }
    bindSingleton<NetworkStateSource> {
        NetworkStateSourceImpl(instance(), instance())
    }
}