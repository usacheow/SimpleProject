package com.usacheow.coreuikit.base

import androidx.fragment.app.FragmentActivity

interface Router {

    fun moveToBack(activity: FragmentActivity) {
        activity.onBackPressed()
    }

    fun close(activity: FragmentActivity) {
        activity.finish()
    }
}