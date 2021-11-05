package com.usacheow.coreui.navigation

import android.app.Activity
import androidx.activity.OnBackPressedCallback

fun OnBackPressedCallback.passBackPressedTo(activity: Activity) {
    isEnabled = false
    activity.onBackPressed()
    isEnabled = true
}