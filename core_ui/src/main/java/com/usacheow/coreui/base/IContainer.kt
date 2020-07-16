package com.usacheow.coreui.base

import androidx.fragment.app.Fragment
import androidx.transition.TransitionSet
import com.usacheow.coreui.utils.ext.RoutingTransition

interface IContainer {

    fun show(fragment: Fragment, needAddToBackStack: Boolean = true, transition: TransitionSet = RoutingTransition())

    fun reset()
}