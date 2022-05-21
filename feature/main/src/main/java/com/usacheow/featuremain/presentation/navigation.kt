package com.usacheow.featuremain.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.usacheow.corenavigation.ScreenRoute
import com.usacheow.corenavigation.MainFeatureProvider
import com.usacheow.corenavigation.base.Navigator
import com.usacheow.featuremain.presentation.screen.MainAScreen
import com.usacheow.featuremain.presentation.screen.MainBScreen
import com.usacheow.featuremain.presentation.screen.Mock2Screen
import com.usacheow.featuremain.presentation.screen.MockScreen
import javax.inject.Inject

internal class ScreenNavigator(navHostController: NavHostController) : Navigator(navHostController) {

    fun toMock1SecondScreen() = navHostController.navigate(ScreenRoute.mock1Second.route)

    fun toMock2SecondScreen() = navHostController.navigate(ScreenRoute.mock2Second.route)

    fun toMock3SecondScreen() = navHostController.navigate(ScreenRoute.mock3Second.route)
}

class MainFeatureProviderImpl @Inject constructor() : MainFeatureProvider {

    override fun NavGraphBuilder.mock1Graph(controller: NavHostController) {
        composable(ScreenRoute.mock1.route) { MainAScreen(controller) }
        composable(ScreenRoute.mock1Second.route) { MainBScreen(controller) }
    }

    override fun NavGraphBuilder.mock2Graph(controller: NavHostController) {
        composable(ScreenRoute.mock2.route) { MockScreen(controller) }
        composable(ScreenRoute.mock2Second.route) { MainBScreen(controller) }
    }

    override fun NavGraphBuilder.mock3Graph(controller: NavHostController) {
        composable(ScreenRoute.mock3.route) { Mock2Screen(controller) }
        composable(ScreenRoute.mock3Second.route) { MainBScreen(controller) }
    }
}