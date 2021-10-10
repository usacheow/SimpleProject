package com.usacheow.featuremain.presentation.navigation

import com.usacheow.coremediator.MainMediator
import com.usacheow.coreui.utils.navigation.screen
import com.usacheow.featuremain.R as FeatureR
import javax.inject.Inject

class MainMediatorImpl @Inject constructor() : MainMediator {

    override fun getAFlowDirection() = screen(FeatureR.id.main_nav_graph)

    override fun getMockFlowDirection() = screen(FeatureR.id.mock_nav_graph)
}