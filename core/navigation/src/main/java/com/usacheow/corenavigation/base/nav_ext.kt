package com.usacheow.corenavigation.base

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.usacheow.corenavigation.Deeplink
import com.usacheow.corenavigation.Route

fun NavGraphBuilder.composable(route: Route, vararg deeplink: Deeplink, content: @Composable (NavBackStackEntry) -> Unit) = composable(
    route = route.pattern,
    arguments = route.arguments,
    deepLinks = deeplink.toList().map { data -> navDeepLink { uriPattern = data.pattern } },
    content = content,
)

fun NavController.navigate(route: Route, builder: NavOptionsBuilder.() -> Unit = {}) = navigate(
    route = route.path(),
    builder = builder,
)

inline infix fun <reified ARG> NavController.navigate(direction: FeatureNavDirection<ARG>) = navigate(
    route = direction.route.path(direction.arg.toDefaultFormat()),
    builder = direction.optionsBuilder,
)