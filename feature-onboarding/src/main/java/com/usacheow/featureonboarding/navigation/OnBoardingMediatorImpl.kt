package com.usacheow.featureonboarding.navigation

import android.os.Bundle
import com.usacheow.core.navigation.FeatureNavDirection
import com.usacheow.coremediator.OnBoardingMediator
import com.usacheow.coreui.utils.navigation.WITH
import com.usacheow.coreui.utils.navigation.addNextScreenDirection
import com.usacheow.coreui.utils.navigation.screen
import com.usacheow.featureonboarding.R
import javax.inject.Inject

class OnBoardingMediatorImpl @Inject constructor() : OnBoardingMediator {

    override fun getOnBoardingFlowDirection(
        nextScreenDirection: FeatureNavDirection,
    ) = screen(R.id.on_boarding_nav_graph) WITH Bundle().addNextScreenDirection(nextScreenDirection)
}