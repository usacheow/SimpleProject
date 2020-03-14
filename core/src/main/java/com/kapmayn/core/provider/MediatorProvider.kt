package com.kapmayn.core.provider

import com.kapmayn.core.mediator.HelloMediator

interface MediatorProvider {

    fun provideHelloMediator(): HelloMediator
}