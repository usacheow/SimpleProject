package com.kapmayn.diproviders.provider

import com.kapmayn.diproviders.mediator.HelloMediator

interface MediatorProvider {

    fun provideHelloMediator(): HelloMediator
}