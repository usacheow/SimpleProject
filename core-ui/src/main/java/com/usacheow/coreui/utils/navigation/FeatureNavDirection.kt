package com.usacheow.coreui.utils.navigation

import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.IdRes
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import kotlinx.parcelize.Parcelize

private const val NEXT_SCREEN_DIRECTION_KEY = "NEXT_SCREEN_DIRECTION_KEY"

@Parcelize
data class FeatureNavDirection(
    @IdRes override val actionId: Int,
    override val arguments: Bundle = Bundle(),

    val resetTo: ResetTo? = null,
) : NavDirections, Parcelable

@Parcelize
data class ResetTo(
    @IdRes val destinationId: Int,
    val inclusive: Boolean = true,
) : Parcelable

fun NavDirections.toFeatureNavDirection() = FeatureNavDirection(actionId, arguments)

val currentScreen: Int? = null

fun notInclusive(
    @IdRes destinationId: Int,
) = ResetTo(destinationId, false)

fun screen(@IdRes actionId: Int) = FeatureNavDirection(actionId = actionId)

fun from(navController: NavController) = navController

fun Bundle.addNextScreenDirection(direction: NavDirections) = apply {
    putParcelable(NEXT_SCREEN_DIRECTION_KEY, direction as Parcelable)
}

fun SavedStateHandle.requireNextScreenDirection(): FeatureNavDirection {
    return requireNotNull(get<FeatureNavDirection>(NEXT_SCREEN_DIRECTION_KEY))
}

/*
* screen(ID) with BUNDLE openIn navController
* screen(ID) with BUNDLE replacing ID openIn navController
* screen(ID) with BUNDLE replacing notInclusive(ID) openIn navController
* screen(ID) with BUNDLE openIn navController
*
* The popBackStack ID from navController
* The popBackStack notInclusive(ID) from navController
* The popBackStack currentScreen from navController
* */

infix fun FeatureNavDirection.WITH(arguments: Bundle) = copy(arguments = arguments)

infix fun FeatureNavDirection.REPLACING(
    @IdRes destinationId: Int,
) = this REPLACING ResetTo(destinationId, true)

infix fun FeatureNavDirection.REPLACING(
    resetTo: ResetTo,
) = copy(resetTo = resetTo)

infix fun FeatureNavDirection.OPEN_IN(
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

infix fun NavController.POP_BACK_STACK(
    @IdRes destinationRes: Int?,
) = destinationRes?.let { this POP_BACK_STACK ResetTo(destinationRes, true) } ?: this.popBackStack()

infix fun NavController.POP_BACK_STACK(
    resetTo: ResetTo,
) = this.popBackStack(resetTo.destinationId, resetTo.inclusive)