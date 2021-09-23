package com.usacheow.coreui.utils.navigation

import androidx.annotation.IdRes
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.navOptions
import com.usacheow.coreui.R

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