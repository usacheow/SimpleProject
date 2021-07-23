package com.usacheow.corebilling

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface BillingModule {

    @Binds
    @Singleton
    fun simpleBilling(billing: SimpleBillingImpl): SimpleBilling

    @Binds
    @Singleton
    fun billingMediator(simpleBilling: SimpleBilling): BillingRouter
}