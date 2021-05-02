package com.usacheow.featureonboarding.navigation

import com.usacheow.coremediator.OnBoardingMediator
import com.usacheow.featureonboarding.fragment.OnBoardingFragment
import javax.inject.Inject

class OnBoardingMediatorImpl
@Inject constructor() : OnBoardingMediator {

    override fun getOnBoardingScreen() = OnBoardingFragment.newInstance()
}