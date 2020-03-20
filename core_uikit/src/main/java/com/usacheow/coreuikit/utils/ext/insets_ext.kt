package com.usacheow.coreuikit.utils.ext

import android.os.Build
import android.view.View
import android.view.WindowInsets
import androidx.annotation.RequiresApi
import com.usacheow.coreuikit.utils.ifSupportLollipop

data class PaddingValue(
    val left: Int,
    val top: Int,
    val right: Int,
    val bottom: Int
)

fun View.getInitialPadding() = PaddingValue(paddingLeft, paddingTop, paddingRight, paddingBottom)

fun View.doOnApplyWindowInsets(block: (insets: WindowInsets, padding: PaddingValue) -> Unit) {
    ifSupportLollipop {
        val initialPadding = getInitialPadding()
        setOnApplyWindowInsetsListener { view, insets ->
            block(insets, initialPadding)
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