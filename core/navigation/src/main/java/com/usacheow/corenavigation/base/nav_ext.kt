package com.usacheow.corenavigation.base

import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.usacheow.corenavigation.Route
import com.usacheow.corenavigation.RouteWithArg
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

const val ARG_KEY = "arg"

inline fun <reified ARG> ARG?.toQuery() = ARG_KEY + "=" + this?.let { Json.encodeToString(it) }
inline fun <reified ARG> SavedStateHandle.getArg(): ARG? = this.get<String>(ARG_KEY)?.let { Json.decodeFromString(it) }
inline fun <reified ARG> SavedStateHandle.requireArgs(): ARG = requireNotNull(getArg())

fun createRoute(route: Route) = route.path
fun createRouteWithArg(route: RouteWithArg) = "${route.path}?$ARG_KEY={$ARG_KEY}"
inline fun <reified ARG> createRouteWithArg(route: RouteWithArg, arg: ARG) = "${route.path}?${arg.toQuery()}"

fun NavGraphBuilder.composable(route: Route, content: @Composable (NavBackStackEntry) -> Unit) = composable(
    route = createRoute(route),
    content = content,
)
fun NavGraphBuilder.composable(route: RouteWithArg, content: @Composable (NavBackStackEntry) -> Unit) = composable(
    route = createRouteWithArg(route),
    arguments = listOf(navArgument(ARG_KEY) { type = NavType.StringType }),
    content = content,
)

fun NavController.navigate(route: Route, builder: NavOptionsBuilder.() -> Unit = {}) = navigate(
    route = route.path,
    builder = builder,
)
inline infix fun <reified ARG> NavController.navigate(direction: FeatureNavDirection<ARG>) = navigate(
    route = createRouteWithArg(direction.route, direction.arg),
    builder = direction.optionsBuilder,
)