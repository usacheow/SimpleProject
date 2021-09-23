package com.usacheow.featureonboarding.navigation

import com.usacheow.coremediator.OnBoardingMediator
import com.usacheow.coreui.utils.navigation.screen
import com.usacheow.featureonboarding.R
import javax.inject.Inject

class OnBoardingMediatorImpl @Inject constructor() : OnBoardingMediator {

    override fun getOnBoardingFlowDirection() = screen(R.id.on_boarding_nav_graph)
}