package com.usacheow.featureonboarding.navigation

import com.usacheow.coremediator.FeatureNavDirection
import com.usacheow.coremediator.OnBoardingMediator
import com.usacheow.featureonboarding.R
import javax.inject.Inject

class OnBoardingMediatorImpl @Inject constructor() : OnBoardingMediator {

    override fun getOnBoardingFlowDirection() = FeatureNavDirection(R.id.on_boarding_nav_graph)
}