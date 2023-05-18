package com.usacheow.showcasebilling

import com.usacheow.coredata.network.LocalDispatchers
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.delegate
import org.kodein.di.instance

val billingDiModule by DI.Module {
    bindSingleton<SimpleBilling> {
        SimpleBillingImpl(instance(), instance(), instance(LocalDispatchers.IO))
    }
    delegate<BillingRouter>().to<SimpleBilling>()
    bindSingleton<PurchaseStateProvider> { PurchaseStateProviderImpl(instance()) }
}