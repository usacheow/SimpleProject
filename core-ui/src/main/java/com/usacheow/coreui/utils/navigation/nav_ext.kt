package com.usacheow.coreui.utils.navigation

import androidx.annotation.IdRes
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.navOptions
import com.usacheow.coreui.R

fun defaultNavOptions(block: NavOptionsBuilder.() -> Unit = {}) = navOptions {
    block()
    anim {
        enter = R.animator.nav_default_enter_anim
        exit = R.animator.nav_default_exit_anim
        popEnter = R.animator.nav_default_pop_enter_anim
        popExit = R.animator.nav_default_pop_exit_anim
    }
}

fun resetNavOptions(@IdRes to: Int) = defaultNavOptions {
    popUpTo(to) {
        inclusive = true
    }
    launchSingleTop = true
}