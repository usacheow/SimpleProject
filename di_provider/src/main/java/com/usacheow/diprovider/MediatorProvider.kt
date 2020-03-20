package com.usacheow.diprovider

import com.usacheow.core.mediator.AuthorizationMediator
import com.usacheow.core.mediator.HelloMediator

interface MediatorProvider {

    fun provideHelloMediator(): HelloMediator

    fun provideAuthorizationMediator(): AuthorizationMediator
}