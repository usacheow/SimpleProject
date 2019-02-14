package com.kapmayn.coreuikit.utils

import android.app.Activity
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager

inline fun supportsLollipop(action: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        action()
    }
}

inline fun supportsMarshmallow(action: () -> Unit): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        action()
        return true
    }
    return false
}

fun View.hideKeyboard() {
    val inputMethodManger = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager?
    inputMethodManger!!.hideSoftInputFromWindow(windowToken, 0)
}