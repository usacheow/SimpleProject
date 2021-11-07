package com.usacheow.featureonboarding.navigation

import android.os.Bundle
import com.usacheow.core.navigation.FeatureNavDirection
import com.usacheow.coremediator.OnBoardingFeatureProvider
import com.usacheow.coreui.utils.navigation.WITH
import com.usacheow.coreui.utils.navigation.addArgs
import com.usacheow.coreui.utils.navigation.addNextScreenDirection
import com.usacheow.coreui.utils.navigation.screen
import com.usacheow.featureonboarding.R as FeatureR
import javax.inject.Inject

class OnBoardingFeatureProviderImpl @Inject constructor() : OnBoardingFeatureProvider {

    override fun getOnBoardingFlowDirection(
        args: OnBoardingFeatureProvider.OnBoardingArgs,
        nextScreenDirection: FeatureNavDirection,
    ) = screen(FeatureR.id.on_boarding_nav_graph) WITH
            Bundle().addNextScreenDirection(nextScreenDirection).addArgs(args)
}