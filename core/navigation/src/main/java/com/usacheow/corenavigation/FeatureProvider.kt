package com.usacheow.corenavigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface MainFeatureProvider {

    fun NavGraphBuilder.mock1Graph(controller: NavHostController)

    fun NavGraphBuilder.mock2Graph(controller: NavHostController)

    fun NavGraphBuilder.mock3Graph(controller: NavHostController)
}

interface BottomBarFeatureProvider {

    fun NavGraphBuilder.bottomBarGraph(route: Route, items: List<ScreenItem>)

    data class ScreenItem(
        @DrawableRes val iconRes: Int,
        @StringRes val labelRes: Int,
        val route: Route,
        val startDestination: Route,
        val builder: NavGraphBuilder.(NavHostController) -> Unit,
    )
}

interface ExampleFeatureProvider {

    fun NavGraphBuilder.exampleGraph(controller: NavHostController)
}