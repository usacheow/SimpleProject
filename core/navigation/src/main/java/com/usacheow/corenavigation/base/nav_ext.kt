package com.usacheow.corenavigation.base

import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

const val ARG_KEY = "arg"

inline fun <reified ARG> ARG?.toQuery() = ARG_KEY + "=" + this?.let { Json.encodeToString(it) }
inline fun <reified ARG> SavedStateHandle.getArg(): ARG? = this.get<String>(ARG_KEY)?.let { Json.decodeFromString(it) }
inline fun <reified ARG> SavedStateHandle.requireArgs(): ARG = requireNotNull(getArg())

fun NavGraphBuilder.composableWithArg(route: String, content: @Composable (NavBackStackEntry) -> Unit) {
    composable(
        "$route?$ARG_KEY={$ARG_KEY}",
        arguments = listOf(navArgument(ARG_KEY) { type = NavType.StringType }),
        content = content,
    )
}

inline infix fun <reified ARG> NavController.navigateWithArg(direction: FeatureNavDirection<ARG>) {
    val route = "${direction.route}?${direction.arg.toQuery()}"
    navigate(route, builder = direction.optionsBuilder)
}