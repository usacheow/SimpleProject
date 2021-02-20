package com.usacheow.coreui.utils.view

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.fragment.app.Fragment

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


fun Fragment.getDisplayWidthPixels() = activity?.getDisplayWidthPixels() ?: 0

fun Fragment.getDisplayWidth(screenSize: Point = Point()) = activity?.getDisplayWidth(screenSize) ?: 0