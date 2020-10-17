package com.usacheow.coreui.base

import androidx.fragment.app.Fragment
import androidx.transition.TransitionSet
import com.usacheow.coreui.utils.view.RoutingTransition

interface IContainer {

    fun show(fragment: Fragment, needAddToBackStack: Boolean = true, needAnimate: Boolean = true)

    fun reset()
}