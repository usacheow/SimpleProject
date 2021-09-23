package com.usacheow.coreui.base

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.usacheow.coreui.utils.navigation.from
import com.usacheow.coreui.utils.navigation.popBackStack

abstract class Router(protected val fragment: Fragment) {

    protected val navController get() = fragment.findNavController()

    fun moveToBack() {
        popBackStack().from(navController)
    }
}