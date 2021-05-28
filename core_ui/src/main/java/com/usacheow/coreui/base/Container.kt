package com.usacheow.coreui.base

import androidx.fragment.app.Fragment

interface Container {

    fun navigateTo(fragment: Fragment, needAddToBackStack: Boolean = true, needAnimate: Boolean = true, needReplace: Boolean = true)

    fun closeContainer()

    fun resetContainer()
}