package com.usacheow.coreui.base

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.usacheow.coreui.utils.navigation.from
import com.usacheow.coreui.utils.navigation.popBackStack

abstract class Router(protected val fragment: Fragment) {

    protected val navController get() = fragment.findNavController()

    fun back() {
        fragment.requireActivity().onBackPressed()
    }

    fun backTo(@IdRes id: Int) {
        popBackStack(id, inclusive = false).from(navController)
    }

    fun backToRoot() {
        popBackStack(navController.graph.startDestinationId, inclusive = false).from(navController)
    }
}