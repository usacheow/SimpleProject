package com.kapmayn.coreuikit.utils

import android.text.SpannedString
import android.widget.TextView
import androidx.annotation.StringRes

fun TextView.populate(s: SpannedString?) {
    if (s.isNullOrEmpty()) {
        makeGone()
    } else {
        text = s
        makeVisible()
    }
}

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