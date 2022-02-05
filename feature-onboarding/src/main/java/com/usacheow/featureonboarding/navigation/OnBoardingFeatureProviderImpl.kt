package com.usacheow.featureonboarding.navigation

import android.os.Bundle
import com.usacheow.core.navigation.FeatureNavDirection
import com.usacheow.coremediator.OnBoardingFeatureProvider
import com.usacheow.coreui.navigation.WITH
import com.usacheow.coreui.navigation.addArgs
import com.usacheow.coreui.navigation.addNextScreenDirection
import com.usacheow.coreui.navigation.screen
import javax.inject.Inject
import com.usacheow.featureonboarding.R as FeatureR

class OnBoardingFeatureProviderImpl @Inject constructor() : OnBoardingFeatureProvider {

    override fun getOnBoardingFlowDirection(
        args: OnBoardingFeatureProvider.OnBoardingArgs,
        nextScreenDirection: FeatureNavDirection,
    ) = screen(FeatureR.id.on_boarding_nav_graph) WITH
        Bundle().addNextScreenDirection(nextScreenDirection).addArgs(args)
}