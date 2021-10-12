package com.usacheow.coreui.uikit.helper

import android.view.View

fun View.makeVisible() {
    visibility = View.VISIBLE
}

fun View.makeInvisible() {
    visibility = View.INVISIBLE
}

fun View.makeGone() {
    visibility = View.GONE
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

fun View.doOnClick(action: (() -> Unit)?) {
    isEnabled = if (action == null) {
        setOnClickListener(null)
        false
    } else {
        setOnClickListener { action() }
        true
    }
}

fun View.doOnClick(isShimmer: Boolean, listener: (() -> Unit)?) = doOnClick(
    when {
        isShimmer -> null
        else -> listener
    }
)