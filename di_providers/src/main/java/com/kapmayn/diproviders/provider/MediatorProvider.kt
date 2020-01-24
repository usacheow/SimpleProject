package com.kapmayn.diproviders.provider

import com.kapmayn.diproviders.mediator.HelloMediator
import com.kapmayn.diproviders.mediator.WorldMediator

interface MediatorProvider {

    fun provideHelloMediator(): HelloMediator

    fun provideWorldMediator(): WorldMediator
}