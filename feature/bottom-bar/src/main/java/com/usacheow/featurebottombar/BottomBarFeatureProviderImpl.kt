package com.usacheow.featurebottombar

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.usacheow.corenavigation.BottomBarFeatureProvider
import com.usacheow.corenavigation.FeatureRoute
import javax.inject.Inject

class BottomBarFeatureProviderImpl @Inject constructor() : BottomBarFeatureProvider {

    override fun NavGraphBuilder.bottomBarGraph(route: FeatureRoute, items: List<BottomBarFeatureProvider.ScreenItem>) {
        composable(route = route.route) { BottomBarScreen(items) }
    }
}