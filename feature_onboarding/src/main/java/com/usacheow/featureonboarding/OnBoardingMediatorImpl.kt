package com.usacheow.featureonboarding

import com.usacheow.coremediator.OnBoardingMediator
import javax.inject.Inject

class OnBoardingMediatorImpl
@Inject constructor() : OnBoardingMediator {

    override fun getOnBoardingScreen() = OnBoardingFragment.newInstance()
}