package com.usacheow.featureonboarding.navigation

import android.os.Bundle
import com.usacheow.corecommon.navigation.FeatureNavDirection
import com.usacheow.corenavigation.OnBoardingFeatureProvider
import com.usacheow.corenavigation.base.WITH
import com.usacheow.corenavigation.base.addArgs
import com.usacheow.corenavigation.base.addNextScreenDirection
import com.usacheow.corenavigation.base.screen
import javax.inject.Inject
import com.usacheow.featureonboarding.R as FeatureR

class OnBoardingFeatureProviderImpl @Inject constructor() : OnBoardingFeatureProvider {

    override fun getOnBoardingFlowDirection(
        args: OnBoardingFeatureProvider.OnBoardingArgs,
        nextScreenDirection: FeatureNavDirection,
    ) = screen(FeatureR.id.on_boarding_nav_graph) WITH
        Bundle().addNextScreenDirection(nextScreenDirection).addArgs(args)
}