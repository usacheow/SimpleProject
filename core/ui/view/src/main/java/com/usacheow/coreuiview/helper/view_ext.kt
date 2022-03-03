package com.usacheow.coreuiview.helper

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