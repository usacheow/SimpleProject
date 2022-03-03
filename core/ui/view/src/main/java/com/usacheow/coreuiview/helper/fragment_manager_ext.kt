package com.usacheow.coreuiview.helper

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

fun DialogFragment.showIfCan(fragmentManager: FragmentManager, tag: String): Boolean {
    if (fragmentManager.findFragmentByTag(tag) == null) {
        show(fragmentManager, tag)
        return true
    }

    return false
}