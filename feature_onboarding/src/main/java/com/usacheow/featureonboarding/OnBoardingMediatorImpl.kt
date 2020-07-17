package com.usacheow.featureonboarding

import com.usacheow.coremediator.mediator.OnBoardingMediator
import javax.inject.Inject

class OnBoardingMediatorImpl
@Inject constructor() : OnBoardingMediator {

    override fun getOnBoardingScreen() = OnBoardingFragment.newInstance()
}