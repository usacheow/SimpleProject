package com.usacheow.featuremain.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.usacheow.corenavigation.AppRoute
import com.usacheow.corenavigation.MainFeatureProvider
import com.usacheow.corenavigation.base.Navigator
import com.usacheow.corenavigation.base.composable
import com.usacheow.corenavigation.base.navigate
import com.usacheow.featuremain.presentation.screen.MainAScreen
import com.usacheow.featuremain.presentation.screen.MainBScreen
import com.usacheow.featuremain.presentation.screen.Mock2Screen
import com.usacheow.featuremain.presentation.screen.MockScreen
import javax.inject.Inject

internal class ScreenNavigator(navHostController: NavHostController) : Navigator(navHostController) {

    fun toMock1SecondScreen() = navHostController.navigate(AppRoute.MainBottomBar.Mock1Second)

    fun toMock2SecondScreen() = navHostController.navigate(AppRoute.MainBottomBar.Mock2Second)

    fun toMock3SecondScreen() = navHostController.navigate(AppRoute.MainBottomBar.Mock3Second)
}

class MainFeatureProviderImpl @Inject constructor() : MainFeatureProvider {

    override fun NavGraphBuilder.mock1Graph(controller: NavHostController) {
        composable(AppRoute.MainBottomBar.Mock1) { MainAScreen(controller) }
        composable(AppRoute.MainBottomBar.Mock1Second) { MainBScreen(controller) }
    }

    override fun NavGraphBuilder.mock2Graph(controller: NavHostController) {
        composable(AppRoute.MainBottomBar.Mock2) { MockScreen(controller) }
        composable(AppRoute.MainBottomBar.Mock2Second) { MainBScreen(controller) }
    }

    override fun NavGraphBuilder.mock3Graph(controller: NavHostController) {
        composable(AppRoute.MainBottomBar.Mock3) { Mock2Screen(controller) }
        composable(AppRoute.MainBottomBar.Mock3Second) { MainBScreen(controller) }
    }
}