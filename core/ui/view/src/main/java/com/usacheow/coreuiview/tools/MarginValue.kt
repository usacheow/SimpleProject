package com.usacheow.coreuiview.tools

import android.view.View
import android.view.ViewGroup
import androidx.annotation.Px
import androidx.core.view.updatePadding

data class MarginValues(
    @Px val top: Int? = null,
    @Px val bottom: Int? = null,
    @Px val start: Int? = null,
    @Px val end: Int? = null,
) {

    constructor(all: Int) : this(all, all, all, all)
    constructor(vertical: Int? = null, horizontal: Int? = null) : this(vertical, vertical, horizontal, horizontal)
}


fun View.applyMargins(margin: MarginValues) {
    layoutParams = (layoutParams as ViewGroup.MarginLayoutParams).apply {
        setMargins(
            margin.start ?: leftMargin,
            margin.top ?: topMargin,
            margin.end ?: rightMargin,
            margin.bottom ?: bottomMargin,
        )
    }
}


fun View.applyPadding(margin: MarginValues) {
    updatePadding(
        left = margin.start ?: paddingLeft,
        top = margin.top ?: paddingTop,
        right = margin.end ?: paddingEnd,
        bottom = margin.bottom ?: paddingBottom,
    )
}