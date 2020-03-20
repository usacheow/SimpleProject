package com.usacheow.coreuikit.base

import androidx.fragment.app.Fragment

interface IContainer {

    fun show(fragment: Fragment, needAddToBackStack: Boolean = true)

    fun reset()
}