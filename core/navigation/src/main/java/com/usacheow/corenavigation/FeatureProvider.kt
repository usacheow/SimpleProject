package com.usacheow.corenavigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface ExampleFeatureProvider {

    fun NavGraphBuilder.exampleGraph(controller: NavHostController)
}