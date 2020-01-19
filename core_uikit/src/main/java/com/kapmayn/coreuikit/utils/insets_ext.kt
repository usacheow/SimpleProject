package com.kapmayn.coreuikit.utils

import android.os.Build
import android.view.View
import android.view.WindowInsets
import androidx.annotation.RequiresApi

data class PaddingValue(
    val left: Int,
    val top: Int,
    val right: Int,
    val bottom: Int
)

fun View.getInitialPadding() = PaddingValue(paddingLeft, paddingTop, paddingRight, paddingBottom)

fun View.doOnApplyWindowInsets(block: View.(insets: WindowInsets, padding: PaddingValue) -> Unit) {
    ifSupportLollipop {
        val initialPadding = getInitialPadding()
        setOnApplyWindowInsetsListener { view, insets ->
            view.block(insets, initialPadding)
            insets
        }
        requestApplyInsetsWhenAttached()
    }
}

fun View.doOnApplyWindowInsets(block: View.(endedPadding: PaddingValue) -> Unit) {
    ifSupportLollipop {
        val initialPadding = getInitialPadding()
        setOnApplyWindowInsetsListener { view, insets ->
            view.block(
                PaddingValue(
                    insets.systemWindowInsetLeft + initialPadding.left,
                    insets.systemWindowInsetTop + initialPadding.top,
                    insets.systemWindowInsetRight + initialPadding.right,
                    insets.systemWindowInsetBottom + initialPadding.bottom
                )
            )
            insets
        }
        requestApplyInsetsWhenAttached()
    }
}

@RequiresApi(Build.VERSION_CODES.LOLLIPOP)
fun View.requestApplyInsetsWhenAttached() {
    if (isAttachedToWindow) {
        requestApplyInsets()
    } else {
        addOnAttachStateChangeListener(object : View.OnAttachStateChangeListener {
            override fun onViewAttachedToWindow(v: View) {
                v.removeOnAttachStateChangeListener(this)
                v.requestApplyInsets()
            }

            override fun onViewDetachedFromWindow(v: View) {}
        })
    }
}