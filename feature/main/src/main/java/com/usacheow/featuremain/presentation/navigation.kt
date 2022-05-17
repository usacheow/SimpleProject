package com.usacheow.featuremain.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.usacheow.corenavigation.FeatureRoute
import com.usacheow.corenavigation.MainFeatureProvider
import com.usacheow.corenavigation.base.FeatureNavDirection
import com.usacheow.corenavigation.base.Navigator
import com.usacheow.corenavigation.base.composableWithArg
import com.usacheow.corenavigation.base.navigateWithArg
import com.usacheow.featuremain.presentation.screen.MainAScreen
import com.usacheow.featuremain.presentation.screen.MainBScreen
import com.usacheow.featuremain.presentation.screen.Mock2Screen
import com.usacheow.featuremain.presentation.screen.MockScreen
import com.usacheow.featuremain.presentation.viewmodels.BViewModel
import javax.inject.Inject

internal enum class ScreenRoute(val route: String) {
    a("a"),
    b("b/"),
    mock("mock"),
    mockB("mockB"),
    mock2("mock2"),
    mock2B("mock2B"),
}

internal class ScreenNavigator(navHostController: NavHostController) : Navigator(navHostController) {

    fun toBScreen(index: String) = navHostController navigateWithArg FeatureNavDirection(
        ScreenRoute.b.route,
        BViewModel.Args(index),
    )

    fun toMockBScreen() = navHostController.navigate(ScreenRoute.mockB.route)

    fun toMock2BScreen() = navHostController.navigate(ScreenRoute.mock2B.route)
}

class MainFeatureProviderImpl @Inject constructor() : MainFeatureProvider {

    override fun NavGraphBuilder.mainGraph(route: FeatureRoute, controller: NavHostController) {
        navigation(
            route = route.route,
            startDestination = ScreenRoute.a.route,
        ) {
            composable(ScreenRoute.a.route) { MainAScreen(route.route, controller) }
            composableWithArg(ScreenRoute.b.route) { MainBScreen(route.route, it, controller) }
        }
    }

    override fun NavGraphBuilder.mockGraph(route: FeatureRoute, controller: NavHostController) {
        navigation(
            route = route.route,
            startDestination = ScreenRoute.mock.route,
        ) {
            composable(ScreenRoute.mock.route) { MockScreen(controller) }
            composable(ScreenRoute.mockB.route) { MainBScreen(route.route, it, controller) }
        }
    }

    override fun NavGraphBuilder.mock2Graph(route: FeatureRoute, controller: NavHostController) {
        navigation(
            route = route.route,
            startDestination = ScreenRoute.mock2.route,
        ) {
            composable(ScreenRoute.mock2.route) { Mock2Screen(controller) }
            composable(ScreenRoute.mock2B.route) { MainBScreen(route.route, it, controller) }
        }
    }
}