package com.usacheow.featureexample.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.usacheow.corenavigation.ExampleFeatureProvider
import com.usacheow.corenavigation.ScreenRoute
import com.usacheow.corenavigation.base.Navigator
import com.usacheow.featureexample.presentation.screen.FirstScreen
import com.usacheow.featureexample.presentation.screen.SecondScreen
import javax.inject.Inject

internal class ScreenNavigator(navHostController: NavHostController) : Navigator(navHostController) {

    fun toOnBoardingInfoScreen() = navHostController.navigate(ScreenRoute.exampleSecond.route)
}

class ExampleFeatureProviderImpl @Inject constructor() : ExampleFeatureProvider {

    override fun NavGraphBuilder.exampleGraph(controller: NavHostController) {
        composable(ScreenRoute.exampleFirst.route) { FirstScreen(controller) }
        composable(ScreenRoute.exampleSecond.route) { SecondScreen(controller) }
    }
}