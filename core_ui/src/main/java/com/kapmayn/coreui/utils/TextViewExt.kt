package com.kapmayn.coreui.utils

import android.widget.TextView
import androidx.annotation.StringRes

fun TextView.populate(s: String?) {
    if (s.isNullOrEmpty()) {
        makeGone()
    } else {
        text = s
        makeVisible()
    }
}

fun TextView.populate(@StringRes textId: Int) {
    populate(resources.getString(textId))
}