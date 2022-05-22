package com.usacheow.featurebottombar

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.usacheow.corenavigation.BottomBarFeatureProvider
import com.usacheow.corenavigation.Route
import com.usacheow.corenavigation.base.createRoute
import javax.inject.Inject

class BottomBarFeatureProviderImpl @Inject constructor() : BottomBarFeatureProvider {

    override fun NavGraphBuilder.bottomBarGraph(route: Route, items: List<BottomBarFeatureProvider.ScreenItem>) {
        composable(route = createRoute(route)) { BottomBarScreen(items) }
    }
}