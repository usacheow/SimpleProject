package com.usacheow.coreui.utils.system

import android.os.Build

inline fun ifSupportLollipop(action: () -> Unit) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        action()
    }
}

inline fun ifSupportMarshmallow(action: () -> Unit): Boolean {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        action()
        return true
    }
    return false
}