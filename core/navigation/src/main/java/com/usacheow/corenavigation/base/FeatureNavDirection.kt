package com.usacheow.corenavigation.base

import androidx.navigation.NavOptionsBuilder

data class FeatureNavDirection<ARG>(
    val route: String,
    val clazz: Class<ARG>,
    val arg: ARG? = null,
    val optionsBuilder: NavOptionsBuilder.() -> Unit = {},
)

inline fun <reified ARG> FeatureNavDirection(
    route: String,
    arg: ARG? = null,
    noinline optionsBuilder: NavOptionsBuilder.() -> Unit = {},
) = FeatureNavDirection(route, ARG::class.java, arg, optionsBuilder)