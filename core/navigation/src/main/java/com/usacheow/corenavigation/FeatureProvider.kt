package com.usacheow.corenavigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface OnBoardingFeatureProvider {

    fun NavGraphBuilder.onBoardingGraph(route: FeatureRoute, controller: NavHostController)
}

interface MainFeatureProvider {

    fun NavGraphBuilder.mainGraph(route: FeatureRoute, controller: NavHostController)

    fun NavGraphBuilder.mockGraph(route: FeatureRoute, controller: NavHostController)

    fun NavGraphBuilder.mock2Graph(route: FeatureRoute, controller: NavHostController)
}

interface BottomBarFeatureProvider {

    fun NavGraphBuilder.bottomBarGraph(route: FeatureRoute, items: List<ScreenItem>)

    data class ScreenItem(
        @DrawableRes val iconRes: Int,
        @StringRes val labelRes: Int,
        val route: FeatureRoute,
        val builder: NavGraphBuilder.(NavHostController) -> Unit,
    )
}