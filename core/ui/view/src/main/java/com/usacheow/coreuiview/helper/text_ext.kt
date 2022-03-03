package com.usacheow.coreuiview.helper

import android.widget.TextView
import com.usacheow.corecommon.resource.TextSource

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