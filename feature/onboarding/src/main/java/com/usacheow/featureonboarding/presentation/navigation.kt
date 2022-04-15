package com.usacheow.featureonboarding.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.usacheow.corenavigation.FeatureRoute
import com.usacheow.corenavigation.OnBoardingFeatureProvider
import com.usacheow.corenavigation.base.Navigator
import com.usacheow.featureonboarding.presentation.screen.OnBoardingInfoScreen
import com.usacheow.featureonboarding.presentation.screen.OnBoardingScreen
import javax.inject.Inject

internal enum class ScreenRoute(val route: String) {
    welcome("welcome"),
    info("info"),
}

internal class ScreenNavigator(navHostController: NavHostController) : Navigator(navHostController) {

    fun toOnBoardingInfoScreen() = navHostController.navigate(ScreenRoute.info.route)

    fun toMainFeature() = navHostController.navigate(FeatureRoute.main.route)
}

class OnBoardingFeatureProviderImpl @Inject constructor() : OnBoardingFeatureProvider {

    override fun NavGraphBuilder.onBoardingGraph(route: FeatureRoute, controller: NavHostController) {
        navigation(
            route = route.route,
            startDestination = ScreenRoute.welcome.route,
        ) {
            composable(ScreenRoute.welcome.route) { OnBoardingScreen(controller) }
            composable(ScreenRoute.info.route) { OnBoardingInfoScreen(controller) }
        }
    }
}