package com.usacheow.corenavigation

import androidx.annotation.MenuRes
import androidx.annotation.NavigationRes
import com.usacheow.corecommon.navigation.FeatureNavDirection

interface BottomBarFeatureProvider {

    fun getBottomBarFlowDirection(
        @MenuRes menuRes: Int,
        @NavigationRes graphRes: Int,
    ): FeatureNavDirection
}