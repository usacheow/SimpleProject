package com.usacheow.coreuiview.tools

import android.widget.TextView
import com.usacheow.corecommon.container.TextSource
import com.usacheow.coreuiview.tools.resource.get

fun TextView.populate(source: TextSource?) {
    when (source) {
        null -> makeGone()
        else -> populate(source.get(context))
    }
}

fun TextView.populate(s: CharSequence) {
    text = s
    when {
        s.isEmpty() -> makeGone()
        else -> makeVisible()
    }
}