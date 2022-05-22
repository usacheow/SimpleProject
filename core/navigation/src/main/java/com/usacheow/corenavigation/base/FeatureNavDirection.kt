package com.usacheow.corenavigation.base

import androidx.navigation.NavOptionsBuilder
import com.usacheow.corenavigation.RouteWithArg

data class FeatureNavDirection<ARG>(
    val route: RouteWithArg,
    val clazz: Class<ARG>,
    val arg: ARG,
    val optionsBuilder: NavOptionsBuilder.() -> Unit = {},
)

inline fun <reified ARG> FeatureNavDirection(
    route: RouteWithArg,
    arg: ARG,
    noinline optionsBuilder: NavOptionsBuilder.() -> Unit = {},
) = FeatureNavDirection(route, ARG::class.java, arg, optionsBuilder)