package com.usacheow.coreuikit.base

import androidx.fragment.app.Fragment
import androidx.transition.AutoTransition
import androidx.transition.TransitionSet

interface IContainer {

    fun show(fragment: Fragment, needAddToBackStack: Boolean = true, transition: TransitionSet = DefaultTransition())

    fun reset()
}

class DefaultTransition : AutoTransition() {
    init {
        duration = 100
    }
}