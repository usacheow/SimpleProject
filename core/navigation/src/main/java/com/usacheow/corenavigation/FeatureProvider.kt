package com.usacheow.corenavigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.usacheow.corecommon.container.IconValue
import com.usacheow.corecommon.strings.StringKey

interface MainFeatureProvider {

    fun NavGraphBuilder.mock1Graph(controller: NavHostController)

    fun NavGraphBuilder.mock2Graph(controller: NavHostController)

    fun NavGraphBuilder.mock3Graph(controller: NavHostController)
}

interface BottomBarFeatureProvider {

    fun NavGraphBuilder.bottomBarGraph(route: Route, items: List<ScreenItem>)

    data class ScreenItem(
        val icon: IconValue,
        val labelKey: StringKey,
        val route: Route,
        val startDestination: Route,
        val builder: NavGraphBuilder.(NavHostController) -> Unit,
    )
}

interface ExampleFeatureProvider {

    fun NavGraphBuilder.exampleGraph(controller: NavHostController)
}