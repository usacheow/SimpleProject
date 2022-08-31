package com.usacheow.featureexample.presentation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.usacheow.corenavigation.AppRoute
import com.usacheow.corenavigation.ExampleFeatureProvider
import com.usacheow.corenavigation.base.FeatureNavDirection
import com.usacheow.corenavigation.base.Navigator
import com.usacheow.corenavigation.base.composable
import com.usacheow.corenavigation.base.navigate
import com.usacheow.featureexample.presentation.screen.FirstScreen
import com.usacheow.featureexample.presentation.screen.SecondScreen
import javax.inject.Inject

internal class ScreenNavigator(navHostController: NavHostController) : Navigator(navHostController) {

    fun toSecondScreen(
        index: String,
    ) = navHostController navigate FeatureNavDirection(AppRoute.ExampleSecond, AppRoute.ExampleSecond.Args(index))

    fun toBottomBarScreen() = navHostController.navigate(AppRoute.MainBottomBar)
}

class ExampleFeatureProviderImpl @Inject constructor() : ExampleFeatureProvider {

    override fun NavGraphBuilder.exampleGraph(controller: NavHostController) {
        composable(AppRoute.ExampleFirst) { FirstScreen(controller) }
        composable(AppRoute.ExampleSecond) { SecondScreen(controller) }
    }
}