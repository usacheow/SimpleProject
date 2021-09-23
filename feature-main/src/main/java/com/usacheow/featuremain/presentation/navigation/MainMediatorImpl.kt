package com.usacheow.featuremain.presentation.navigation

import com.usacheow.coremediator.MainMediator
import com.usacheow.coreui.utils.navigation.screen
import com.usacheow.featuremain.R
import javax.inject.Inject

class MainMediatorImpl @Inject constructor() : MainMediator {

    override fun getAFlowDirection() = screen(R.id.main_nav_graph)

    override fun getMockFlowDirection() = screen(R.id.mock_nav_graph)
}