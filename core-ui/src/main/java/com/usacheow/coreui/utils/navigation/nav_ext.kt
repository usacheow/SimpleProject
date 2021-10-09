package com.usacheow.coreui.utils.navigation

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
import com.usacheow.coreui.R

private const val NEXT_SCREEN_DIRECTION_KEY = "NEXT_SCREEN_DIRECTION_KEY"

fun Bundle.addNextScreenDirection(direction: NavDirections) = apply {
    putParcelable(NEXT_SCREEN_DIRECTION_KEY, direction as Parcelable)
}

fun SavedStateHandle.requireNextScreenDirection(): FeatureNavDirection {
    return requireNotNull(get<FeatureNavDirection>(NEXT_SCREEN_DIRECTION_KEY))
}

fun defaultNavOptions(block: NavOptionsBuilder.() -> Unit = {}) = navOptions {
    block()
    anim {
        enter = R.anim.anim_enter_from_right
        exit = R.anim.anim_exit_to_left
        popEnter = R.anim.anim_enter_from_left
        popExit = R.anim.anim_exit_to_right
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
* screen(ID) with BUNDLE openIn navController
* screen(ID) with BUNDLE replacing ID openIn navController
* screen(ID) with BUNDLE replacing notInclusive(ID) openIn navController
* screen(ID) with BUNDLE openIn navController
*
* The popBackStack ID from navController
* The popBackStack notInclusive(ID) from navController
* The popBackStack currentScreen from navController
* */

val currentScreen: Int? = null

fun screen(@IdRes actionId: Int) = FeatureNavDirection(actionId = actionId)
fun notInclusive(@IdRes destinationId: Int) = ResetTo(destinationId, false)
fun from(navController: NavController) = navController

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