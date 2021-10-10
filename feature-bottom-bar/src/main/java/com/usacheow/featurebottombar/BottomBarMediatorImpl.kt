package com.usacheow.featurebottombar

import androidx.annotation.MenuRes
import androidx.annotation.NavigationRes
import com.usacheow.coremediator.BottomBarMediator
import com.usacheow.coreui.utils.navigation.WITH
import com.usacheow.coreui.utils.navigation.screen
import javax.inject.Inject

class BottomBarMediatorImpl @Inject constructor() : BottomBarMediator {

    override fun getBottomBarFlowDirection(
        @MenuRes menuRes: Int,
        @NavigationRes graphRes: Int,
    ) = screen(R.id.bottom_bar_nav_graph) WITH BottomBarFragment.bundle(menuRes, graphRes)
}