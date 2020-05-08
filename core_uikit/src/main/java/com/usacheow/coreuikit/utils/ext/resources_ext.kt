package com.usacheow.coreuikit.utils.ext

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.graphics.Point
import android.util.DisplayMetrics
import android.view.View
import android.view.WindowManager
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
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

fun View.string(@StringRes id: Int) = resources.getString(id)

fun View.color(@ColorRes id: Int) = ContextCompat.getColor(context, id)

fun Fragment.string(@StringRes id: Int) = resources.getString(id)

fun Fragment.color(@ColorRes id: Int) = ContextCompat.getColor(context!!, id)

fun Activity.string(@StringRes id: Int) = resources.getString(id)

fun Activity.color(@ColorRes id: Int) = ContextCompat.getColor(this, id)

val Int.toPx: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Fragment.getDisplayWidthPixels() = activity?.getDisplayWidthPixels() ?: 0

fun Fragment.getDisplayWidth(screenSize: Point = Point()) = activity?.getDisplayWidth(screenSize) ?: 0

fun Fragment.isDarkMode(): Boolean {
    return context?.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES
}