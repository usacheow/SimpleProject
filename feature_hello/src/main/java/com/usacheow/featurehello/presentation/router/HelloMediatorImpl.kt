package com.usacheow.featurehello.presentation.router

import com.usacheow.core.mediator.HelloMediator
import com.usacheow.featurehello.presentation.fragment.HelloContainerFragment
import javax.inject.Inject

class HelloMediatorImpl
@Inject constructor() : HelloMediator {

    override fun getHelloContainer() = HelloContainerFragment.newInstance()
}