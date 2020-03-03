package com.kapmayn.coreuikit.utils

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.view.marginBottom
import androidx.core.view.marginTop

fun View.hideKeyboard() {
    val inputMethodManger = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
    inputMethodManger!!.hideSoftInputFromWindow(windowToken, 0)
}

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

fun View.resize(widthDp: Int, heightDp: Int) {
    layoutParams?.let {
        it.width = widthDp.toPx
        it.height = heightDp.toPx
    }
}