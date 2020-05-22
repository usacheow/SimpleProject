package com.usacheow.coreuikit.base

import androidx.fragment.app.Fragment
import androidx.transition.TransitionSet
import com.usacheow.coreuikit.utils.ext.RoutingTransition

interface IContainer {

    fun show(fragment: Fragment, needAddToBackStack: Boolean = true, transition: TransitionSet = RoutingTransition())

    fun reset()
}