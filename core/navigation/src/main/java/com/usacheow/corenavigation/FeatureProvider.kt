package com.usacheow.corenavigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

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

interface ExampleFeatureProvider {

    fun NavGraphBuilder.exampleGraph(route: FeatureRoute, controller: NavHostController)
}