package com.usacheow.featurebottombar

import androidx.annotation.MenuRes
import androidx.annotation.NavigationRes
import com.usacheow.corenavigation.BottomBarFeatureProvider
import com.usacheow.corenavigation.base.WITH
import com.usacheow.corenavigation.base.screen
import javax.inject.Inject
import com.usacheow.featurebottombar.R as FeatureR

class BottomBarFeatureProviderImpl @Inject constructor() : BottomBarFeatureProvider {

    override fun getBottomBarFlowDirection(
        @MenuRes menuRes: Int,
        @NavigationRes graphRes: Int,
    ) = screen(FeatureR.id.bottom_bar_nav_graph) WITH BottomBarFragment.bundle(menuRes, graphRes)
}