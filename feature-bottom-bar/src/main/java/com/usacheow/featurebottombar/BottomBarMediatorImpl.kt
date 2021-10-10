package com.usacheow.featurebottombar

import androidx.annotation.MenuRes
import androidx.annotation.NavigationRes
import com.usacheow.coremediator.BottomBarMediator
import com.usacheow.coreui.utils.navigation.WITH
import com.usacheow.coreui.utils.navigation.screen
import javax.inject.Inject
import com.usacheow.featurebottombar.R as FeatureR

class BottomBarMediatorImpl @Inject constructor() : BottomBarMediator {

    override fun getBottomBarFlowDirection(
        @MenuRes menuRes: Int,
        @NavigationRes graphRes: Int,
    ) = screen(FeatureR.id.bottom_bar_nav_graph) WITH BottomBarFragment.bundle(menuRes, graphRes)
}