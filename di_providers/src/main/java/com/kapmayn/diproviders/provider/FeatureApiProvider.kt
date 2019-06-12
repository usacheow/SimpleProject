package com.kapmayn.diproviders.provider

import com.kapmayn.diproviders.mediator.HelloMediator
import com.kapmayn.diproviders.mediator.WorldMediator

interface FeatureApiProvider {

    fun provideHelloFeatureApi(): HelloMediator

    fun provideWorldFeatureApi(): WorldMediator
}