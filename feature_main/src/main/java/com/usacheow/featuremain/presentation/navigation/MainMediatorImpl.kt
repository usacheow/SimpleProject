package com.usacheow.featuremain.presentation.navigation

import com.usacheow.coremediator.MainMediator
import com.usacheow.featuremain.presentation.fragment.container.HelloContainerFragment
import javax.inject.Inject

class MainMediatorImpl
@Inject constructor() : MainMediator {

    override fun getHelloContainer() = HelloContainerFragment.newInstance()
}