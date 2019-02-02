package com.kapmayn.coreui.utils

import android.view.View
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.core.view.marginTop

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

fun View.isVisible() = visibility == View.VISIBLE

var View.topPadding: Int
    get() = paddingTop
    set(value) {
        setPadding(paddingLeft, value, paddingRight, paddingBottom)
    }

var View.bottomPadding: Int
    get() = paddingBottom
    set(value) {
        setPadding(paddingLeft, paddingTop, paddingRight, value)
    }

fun View.setHorizontalPadding(paddingValue: Int) {
    setPadding(paddingValue, paddingTop, paddingValue, paddingBottom)
}

fun View.setVerticalPadding(paddingValue: Int) {
    setPadding(paddingLeft, paddingValue, paddingRight, paddingValue)
}

var View.topMargin: Int
    get() = marginTop
    set(value) {
        layoutParams = (layoutParams as ViewGroup.MarginLayoutParams).apply {
            topMargin = value
        }
    }

var View.bottomMargin: Int
    get() = marginBottom
    set(value) {
        layoutParams = (layoutParams as ViewGroup.MarginLayoutParams).apply {
            bottomMargin = value
        }
    }