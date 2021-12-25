package com.usacheow.coreui.navigation

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.usacheow.core.navigation.FeatureNavDirection
import com.usacheow.coreui.screen.SimpleActivity
import com.usacheow.coreui.utils.navigation.OPEN_IN
import com.usacheow.coreui.utils.navigation.POP_BACK_STACK
import com.usacheow.coreui.utils.navigation.from
import com.usacheow.coreui.utils.navigation.notInclusive

abstract class Router(protected val fragment: Fragment) {

    protected val navController get() = fragment.findNavController()

    protected val activityNavController get() = (fragment.requireActivity() as? SimpleActivity<*>)?.findNavController()

    protected fun requireActivityNavController(): NavController {
        return activityNavController
            ?: throw IllegalStateException("NavController was not found in activity.")
    }

    fun apply(direction: FeatureNavDirection) {
        direction OPEN_IN navController
    }

    fun back() {
        fragment.requireActivity().onBackPressed()
    }

    fun backTo(@IdRes id: Int) {
        from(navController) POP_BACK_STACK notInclusive(id)
    }

    fun backToRoot() {
        from(navController) POP_BACK_STACK notInclusive(navController.graph.startDestinationId)
    }
}