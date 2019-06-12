package com.kapmayn.core.presentation.base

import androidx.fragment.app.Fragment

interface IContainer {

    fun show(fragment: Fragment, needAddToBackStack: Boolean = false)

    fun reset()

    fun onBackClicked(): Boolean
}