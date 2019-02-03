package com.kapmayn.core.presentation.base

import androidx.fragment.app.FragmentActivity

interface Router {

    fun moveToBack(activity: FragmentActivity) {
        activity.finish()
    }
}