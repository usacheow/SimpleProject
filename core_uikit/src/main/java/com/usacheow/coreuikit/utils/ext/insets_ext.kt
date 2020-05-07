package com.usacheow.coreuikit.utils.ext

import android.graphics.Rect
import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

typealias PaddingValue = Rect

fun View.getInitialPadding() = Rect(paddingLeft, paddingTop, paddingRight, paddingBottom)

fun View.doOnApplyWindowInsets(block: (insets: WindowInsetsCompat, padding: PaddingValue) -> Unit) {
    val initialPadding = getInitialPadding()
    ViewCompat.setOnApplyWindowInsetsListener(this) { _, insets ->
        block(insets, initialPadding)
        insets
    }
//    requestApplyInsetsWhenAttached()
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