package com.kapmayn.coreuikit.utils

import android.os.Build

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