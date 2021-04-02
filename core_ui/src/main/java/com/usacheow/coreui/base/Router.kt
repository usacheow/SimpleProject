package com.usacheow.coreui.base

import androidx.fragment.app.Fragment
import com.usacheow.coreui.fragment.SimpleFragment

abstract class Router(protected val fragment: Fragment) {

    protected val simpleFragment get() = fragment as? SimpleFragment<*>

    fun moveToBack() {
        fragment.requireActivity().onBackPressed()
    }
}