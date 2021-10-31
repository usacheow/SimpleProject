package com.usacheow.coreui.uikit.helper

import android.graphics.Rect
import android.view.View
import android.view.Window
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

typealias PaddingValue = Rect

fun createWindowInsetsControllerCompat(
    window: Window?,
    view: View,
    needWhiteStatusIcons: Boolean,
    needWhiteNavigationIcons: Boolean,
): WindowInsetsControllerCompat? {
    window ?: return null

    WindowCompat.setDecorFitsSystemWindows(window, false)
    return WindowCompat.getInsetsController(window, view).apply {
        this?.isAppearanceLightStatusBars = !needWhiteStatusIcons
        this?.isAppearanceLightNavigationBars = !needWhiteNavigationIcons
    }
}

fun WindowInsetsCompat.isImeVisible() = getImeHeight() != 0
fun WindowInsetsCompat.getImeHeight() = getInsets(WindowInsetsCompat.Type.ime()).bottom
fun WindowInsetsControllerCompat.showIme() = show(WindowInsetsCompat.Type.ime())
fun WindowInsetsControllerCompat.hideIme() = hide(WindowInsetsCompat.Type.ime())

fun WindowInsetsCompat.getStatusBarHeight() = getInsets(WindowInsetsCompat.Type.systemBars()).top
fun WindowInsetsCompat.getNavigationBarHeight() = getInsets(WindowInsetsCompat.Type.systemBars()).bottom

fun WindowInsetsCompat.getTopInset() = getStatusBarHeight()
fun WindowInsetsCompat.getBottomInset(needIme: Boolean = false) = when (needIme && isImeVisible()) {
    true -> getImeHeight()
    false -> getNavigationBarHeight()
}

fun View.doOnApplyWindowInsets(block: (insets: WindowInsetsCompat, padding: PaddingValue) -> WindowInsetsCompat) {
    val initialPadding = Rect(paddingLeft, paddingTop, paddingRight, paddingBottom)
    ViewCompat.setOnApplyWindowInsetsListener(this) { insets, x ->
        block(x, initialPadding)
    }
    requestApplyInsetsWhenAttached()
//    ViewCompat.requestApplyInsets(this)
}

fun View.requestApplyInsetsWhenAttached() {
    if (isAttachedToWindow) {
        ViewCompat.requestApplyInsets(this)
    } else {
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {

            override fun onViewAttachedToWindow(v: View) {
                v.removeOnAttachStateChangeListener(this)
                ViewCompat.requestApplyInsets(v)
            }

            override fun onViewDetachedFromWindow(v: View) = Unit
        })
    }
}