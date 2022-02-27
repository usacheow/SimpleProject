package com.usacheow.coreui.uikit.helper

import android.widget.TextView
import com.usacheow.core.resource.TextSource

fun TextView.populate(source: TextSource?) {
    fun TextView.populate(s: CharSequence) {
        if (s.isEmpty()) {
            makeGone()
        } else {
            text = s
            makeVisible()
        }
    }

    if (source == null) {
        makeGone()
    } else {
        populate(source.toCharSequence(context))
    }
}