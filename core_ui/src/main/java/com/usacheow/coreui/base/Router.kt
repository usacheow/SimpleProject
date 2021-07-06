package com.usacheow.coreui.base

import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.usacheow.coreui.fragment.SimpleFragment

abstract class Router(protected val fragment: Fragment) {

    protected val simpleFragment get() = fragment as? SimpleFragment<*>

    protected fun navigateTo(direction: NavDirections) = fragment.findNavController().navigate(direction)

    fun moveToBack() {
        fragment.requireActivity().onBackPressed()
    }
}