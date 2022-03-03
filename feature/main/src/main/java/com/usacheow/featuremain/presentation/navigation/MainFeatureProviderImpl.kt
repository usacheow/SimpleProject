package com.usacheow.featuremain.presentation.navigation

import com.usacheow.corenavigation.MainFeatureProvider
import com.usacheow.corenavigation.base.screen
import javax.inject.Inject
import com.usacheow.featuremain.R as FeatureR

class MainFeatureProviderImpl @Inject constructor() : MainFeatureProvider {

    override fun getAFlowDirection() = screen(FeatureR.id.main_nav_graph)

    override fun getMockFlowDirection() = screen(FeatureR.id.mock_nav_graph)
}