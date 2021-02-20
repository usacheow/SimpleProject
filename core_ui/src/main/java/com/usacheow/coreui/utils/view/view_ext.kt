package com.usacheow.coreui.utils.view

import android.view.View
import android.view.ViewGroup
import com.usacheow.coreui.utils.*

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeInvisible() {
    visibility = View.INVISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
}

fun View.setVisible(isVisible: Boolean) {
    when {
        isVisible -> makeVisible()
        else -> makeGone()
    }
}

fun View.resize(widthPx: Int = width, heightPx: Int = height) {
    layoutParams?.let {
        it.width = widthPx
        it.height = heightPx
    }
}

/*Set ?selectableItemBackground programmatically

with(TypedValue()) {
    context.theme.resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, this, true)
    setBackgroundResource(resourceId)
}
* */

fun View.doOnClick(action: () -> Unit) {
    setOnClickListener {
        action()
    }
}

fun View.setListenerIfNeed(listener: (() -> Unit)?) {
    isEnabled = if (listener == null) {
        setOnClickListener(null)
        false
    } else {
        doOnClick(listener)
        true
    }
}

fun View.setListenerIfNeed(isShimmer: Boolean, listener: (() -> Unit)?) = setListenerIfNeed(when {
    isShimmer -> null
    else -> listener
})