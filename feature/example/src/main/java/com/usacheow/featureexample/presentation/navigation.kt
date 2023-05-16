package com.usacheow.featureexample.presentation

import cafe.adriel.voyager.navigator.Navigator
import com.usacheow.corenavigation.AppRoute
import com.usacheow.corenavigation.ExampleFeatureProvider
import com.usacheow.corenavigation.base.NavigatorWrapper
import com.usacheow.corenavigation.base.featureGraph
import com.usacheow.corenavigation.base.push
import com.usacheow.corenavigation.base.screen
import com.usacheow.featureexample.presentation.screen.FirstScreen
import com.usacheow.featureexample.presentation.screen.SecondScreen
import javax.inject.Inject

internal class ScreenNavigator(navigator: Navigator) : NavigatorWrapper(navigator) {

    fun toSecondScreen(
        index: String,
    ) = navigator.push(AppRoute.ExampleSecond(index))
}

class ExampleFeatureProviderImpl @Inject constructor() : ExampleFeatureProvider {

    override val exampleGraph = featureGraph {
        screen<AppRoute.ExampleFirst> { FirstScreen() }
        screen<AppRoute.ExampleSecond> { SecondScreen(it.index) }
    }
}