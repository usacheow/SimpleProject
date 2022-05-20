package com.usacheow.featureexample.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.usacheow.corenavigation.ExampleFeatureProvider
import com.usacheow.corenavigation.FeatureRoute
import com.usacheow.corenavigation.base.Navigator
import com.usacheow.featureexample.presentation.screen.FirstScreen
import com.usacheow.featureexample.presentation.screen.SecondScreen
import javax.inject.Inject

internal enum class ScreenRoute(val route: String) {
    first("first"),
    second("second"),
}

internal class ScreenNavigator(navHostController: NavHostController) : Navigator(navHostController) {

    fun toOnBoardingInfoScreen() = navHostController.navigate(ScreenRoute.second.route)
}

class ExampleFeatureProviderImpl @Inject constructor() : ExampleFeatureProvider {

    override fun NavGraphBuilder.exampleGraph(route: FeatureRoute, controller: NavHostController) {
        navigation(
            route = route.route,
            startDestination = ScreenRoute.first.route,
        ) {
            composable(ScreenRoute.first.route) { FirstScreen(controller) }
            composable(ScreenRoute.second.route) { SecondScreen(controller) }
        }
    }
}