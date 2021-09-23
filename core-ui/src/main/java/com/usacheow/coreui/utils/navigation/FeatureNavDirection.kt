package com.usacheow.coreui.utils.navigation

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions

data class FeatureNavDirection(
    @IdRes override val actionId: Int,
    override val arguments: Bundle = Bundle(),

    val resetTo: ResetTo? = null,
) : NavDirections

data class ResetTo(
    @IdRes val destinationId: Int,
    val inclusive: Boolean = true,
)

fun NavDirections.toFeatureNavDirection() = FeatureNavDirection(actionId, arguments)

fun screen(@IdRes actionId: Int) = FeatureNavDirection(actionId = actionId)

fun FeatureNavDirection.with(arguments: Bundle) = copy(arguments = arguments)

fun FeatureNavDirection.replaceAllTo(
    @IdRes destinationId: Int,
    inclusive: Boolean = true,
) = copy(resetTo = ResetTo(destinationId, inclusive))

fun FeatureNavDirection.openIn(
    navController: NavController,
) = navController.navigate(
    this,
    resetTo?.let { resetNavOptions(it.destinationId, it.inclusive) } ?: defaultNavOptions(),
)

// when navOptions is null, value will be overloaded in NavController(line 1293) by params from xml
fun NavDirections.openIn(
    navController: NavController,
    navOptions: NavOptions? = defaultNavOptions(),
) = navController.navigate(this, navOptions)

fun popBackStack(
    @IdRes destinationRes: Int? = null,
    inclusive: Boolean = true,
) = destinationRes?.let { ResetTo(destinationRes, inclusive) }

fun ResetTo?.from(
    navController: NavController,
) = this?.let { navController.popBackStack(destinationId, inclusive) } ?: navController.popBackStack()