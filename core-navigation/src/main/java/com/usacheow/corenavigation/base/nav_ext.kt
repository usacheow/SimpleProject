package com.usacheow.corenavigation.base

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.findNavController
import androidx.navigation.navOptions
import com.usacheow.core.navigation.FeatureNavDirection
import com.usacheow.core.navigation.ResetTo
import com.usacheow.corenavigation.R as CoreNavigationR

fun FragmentActivity.findNavController() = findNavController(CoreNavigationR.id.fragmentContainer)

fun defaultNavOptions(block: NavOptionsBuilder.() -> Unit = {}) = navOptions {
    block()
    anim {
        enter = CoreNavigationR.anim.anim_enter_from_right
        exit = CoreNavigationR.anim.anim_exit_to_left
        popEnter = CoreNavigationR.anim.anim_enter_from_left
        popExit = CoreNavigationR.anim.anim_exit_to_right
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