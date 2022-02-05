package com.usacheow.featuremain.presentation.navigation

import com.usacheow.coremediator.MainFeatureProvider
import com.usacheow.coreui.navigation.screen
import javax.inject.Inject
import com.usacheow.featuremain.R as FeatureR

class MainFeatureProviderImpl @Inject constructor() : MainFeatureProvider {

    override fun getAFlowDirection() = screen(FeatureR.id.main_nav_graph)

    override fun getMockFlowDirection() = screen(FeatureR.id.mock_nav_graph)
}