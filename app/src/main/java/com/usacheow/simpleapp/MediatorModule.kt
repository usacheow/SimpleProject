package com.usacheow.simpleapp

import com.usacheow.coremediator.AuthorizationMediator
import com.usacheow.coremediator.MainMediator
import com.usacheow.coremediator.OnBoardingMediator
import com.usacheow.coremediator.PurchaseMediator
import com.usacheow.featureauth.presentation.navigation.AuthorizationMediatorImpl
import com.usacheow.featuremain.presentation.navigation.MainMediatorImpl
import com.usacheow.featureonboarding.navigation.OnBoardingMediatorImpl
import com.usacheow.featurepurchase.navigation.PurchaseMediatorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface MediatorModule {

    @Binds
    @Singleton
    fun helloMediator(mediator: MainMediatorImpl): MainMediator

    @Binds
    @Singleton
    fun onBoardingMediator(mediator: OnBoardingMediatorImpl): OnBoardingMediator

    @Binds
    @Singleton
    fun authorizationMediator(mediator: AuthorizationMediatorImpl): AuthorizationMediator

    @Binds
    @Singleton
    fun purchaseMediator(mediator: PurchaseMediatorImpl): PurchaseMediator
}