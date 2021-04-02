package com.usacheow.coreui.base

import androidx.fragment.app.Fragment
import androidx.transition.TransitionSet
import com.usacheow.coreui.utils.view.RoutingTransition

interface Container {

    fun navigateTo(fragment: Fragment, needAddToBackStack: Boolean = true, needAnimate: Boolean = true, needReplace: Boolean = true)

    fun closeContainer()

    fun resetContainer()
}