package com.kapmayn.coreui.utils

import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.graphics.Point
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.kapmayn.coreui.R

fun Activity.getDisplayWidthPixels(): Int {
    val displayMetrics = DisplayMetrics()
    val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
    windowManager.defaultDisplay.getMetrics(displayMetrics)
    return displayMetrics.widthPixels
}

fun Activity.getDisplayWidth(screenSize: Point = Point()): Int {
    (getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getSize(screenSize)
    return screenSize.x
}

val Int.toPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Fragment.getDisplayWidthPixels() = activity?.getDisplayWidthPixels() ?: 0

fun Fragment.getDisplayWidth(screenSize: Point = Point()) = activity?.getDisplayWidth(screenSize) ?: 0

fun Activity.getNavigationBarHeight() = resources.getNavigationBarHeight()

fun Activity.getStatusBarHeight() = resources.getStatusBarHeight()

fun Fragment.getNavigationBarHeight() = resources.getNavigationBarHeight()

fun Fragment.getStatusBarHeight() = resources.getStatusBarHeight()

fun View.getNavigationBarHeight() = resources.getNavigationBarHeight()

fun View.getStatusBarHeight() = resources.getStatusBarHeight()

fun Resources.getNavigationBarHeight(): Int {
    val resourceId = getIdentifier("navigation_bar_height", "dimen", "android")
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        if (resourceId > 0) getDimensionPixelSize(resourceId)
        else getDimensionPixelOffset(R.dimen.default_height_navigation_bar)
    } else 0
}

fun Resources.getStatusBarHeight(): Int {
    val resourceId = getIdentifier("status_bar_height", "dimen", "android")
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        if (resourceId > 0) getDimensionPixelSize(resourceId)
        else getDimensionPixelOffset(R.dimen.default_height_status_bar)
    } else 0
}