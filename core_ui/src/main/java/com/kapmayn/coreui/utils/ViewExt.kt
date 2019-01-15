package com.kapmayn.coreui.utils

import android.view.View
import android.view.ViewGroup

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

fun View.setTopPadding(paddingValue: Int) {
    setPadding(paddingLeft, paddingValue, paddingRight, paddingBottom)
}

fun View.setBottomPadding(paddingValue: Int) {
    setPadding(paddingLeft, paddingTop, paddingRight, paddingValue)
}

fun View.setHorizontalPadding(paddingValue: Int) {
    setPadding(paddingValue, paddingTop, paddingValue, paddingBottom)
}

fun View.setVerticalPadding(paddingValue: Int) {
    setPadding(paddingLeft, paddingValue, paddingRight, paddingValue)
}

fun View.setTopMargin(marginValue: Int) {
    layoutParams = (layoutParams as ViewGroup.MarginLayoutParams).apply {
        topMargin = marginValue
    }
}

fun View.setBottomMargin(marginValue: Int) {
    layoutParams = (layoutParams as ViewGroup.MarginLayoutParams).apply {
        bottomMargin = marginValue
    }
}