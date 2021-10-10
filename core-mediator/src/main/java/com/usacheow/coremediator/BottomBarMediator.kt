package com.usacheow.coremediator

import androidx.annotation.MenuRes
import androidx.annotation.NavigationRes
import com.usacheow.core.navigation.FeatureNavDirection

interface BottomBarMediator {

    fun getBottomBarFlowDirection(
        @MenuRes menuRes: Int,
        @NavigationRes graphRes: Int,
    ): FeatureNavDirection
}