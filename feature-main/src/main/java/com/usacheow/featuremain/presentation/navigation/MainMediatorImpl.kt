package com.usacheow.featuremain.presentation.navigation

import com.usacheow.coremediator.FeatureNavDirection
import com.usacheow.coremediator.MainMediator
import com.usacheow.featuremain.R
import javax.inject.Inject

class MainMediatorImpl @Inject constructor() : MainMediator {

    override fun getAFlowDirection() = FeatureNavDirection(R.id.main_nav_graph)

    override fun getMockFlowDirection() = FeatureNavDirection(R.id.mock_nav_graph)
}