package com.usacheow.coreui.navigation

import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.IdRes
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.navOptions
import com.usacheow.core.navigation.FeatureNavDirection
import com.usacheow.core.navigation.ResetTo
import com.usacheow.coreui.R as CoreUiR

private const val ARGS_KEY = "ARGS_KEY"
private const val NEXT_SCREEN_DIRECTION_KEY = "NEXT_SCREEN_DIRECTION_KEY"

fun Bundle.addNextScreenDirection(direction: NavDirections) = apply {
    putParcelable(NEXT_SCREEN_DIRECTION_KEY, direction as Parcelable)
}
fun Bundle.addArgs(args: Parcelable) = apply {
    putParcelable(ARGS_KEY, args)
}

fun SavedStateHandle.getNextScreenDirection(): FeatureNavDirection? =
    get<FeatureNavDirection>(NEXT_SCREEN_DIRECTION_KEY)
fun SavedStateHandle.requireNextScreenDirection(): FeatureNavDirection =
    requireNotNull(getNextScreenDirection())

fun <ARGS : Parcelable> Bundle.getArgs(): ARGS? = getParcelable(ARGS_KEY)
fun <ARGS : Parcelable> Bundle.requireArgs(): ARGS = requireNotNull(getArgs())
fun <ARGS : Parcelable> SavedStateHandle.getArgs(): ARGS? = get<ARGS>(ARGS_KEY)
fun <ARGS : Parcelable> SavedStateHandle.requireArgs(): ARGS = requireNotNull(getArgs())

fun defaultNavOptions(block: NavOptionsBuilder.() -> Unit = {}) = navOptions {
    block()
    anim {
        enter = CoreUiR.anim.anim_enter_from_right
        exit = CoreUiR.anim.anim_exit_to_left
        popEnter = CoreUiR.anim.anim_enter_from_left
        popExit = CoreUiR.anim.anim_exit_to_right
    }
}

fun resetNavOptions(@IdRes to: Int, inclusive: Boolean = true) = defaultNavOptions {
    popUpTo(to) {
        this.inclusive = inclusive
    }
    launchSingleTop = true
}

// when navOptions is null, value will be overloaded in NavController(line 1293) by params from xml
fun NavDirections.openIn(
    navController: NavController,
    navOptions: NavOptions? = defaultNavOptions(),
) = navController.navigate(this, navOptions)

/*
* screen(#id) WITH #bundle OPEN_IN #navController
* screen(#id) WITH #bundle OPEN_IN #navController
*
* screen(#id) WITH #bundle REPLACING #id OPEN_IN #navController
* screen(#id) WITH #bundle REPLACING notInclusive(#id) OPEN_IN #navController
*
* #navController POP_BACK_STACK #id
* #navController POP_BACK_STACK notInclusive(#id)
* #navController POP_BACK_STACK currentScreen
* */

val currentScreen: Int? = null

fun screen(@IdRes actionId: Int) = FeatureNavDirection(actionId = actionId)
fun notInclusive(@IdRes destinationId: Int) = ResetTo(destinationId, false)

infix fun FeatureNavDirection.WITH(arguments: Bundle) = copy(arguments = arguments)

infix fun FeatureNavDirection.REPLACING(@IdRes destinationId: Int) = this REPLACING ResetTo(destinationId, true)
infix fun FeatureNavDirection.REPLACING(resetTo: ResetTo) = copy(resetTo = resetTo)

infix fun FeatureNavDirection.OPEN_IN(navController: NavController) = navController.navigate(
    this,
    resetTo?.let { resetNavOptions(it.destinationId, it.inclusive) } ?: defaultNavOptions(),
)

infix fun NavController.POP_BACK_STACK(resetTo: ResetTo) = this.popBackStack(resetTo.destinationId, resetTo.inclusive)
infix fun NavController.POP_BACK_STACK(@IdRes destinationRes: Int?) = destinationRes
    ?.let { this POP_BACK_STACK ResetTo(destinationRes, true) }
    ?: this.popBackStack()