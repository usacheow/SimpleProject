package com.usacheow.coreui.utils

import android.widget.TextView
import androidx.annotation.ColorRes
import com.usacheow.coreui.utils.view.color

data class TextInfo(
    val source: TextSource,
    @ColorRes val colorResId: Int? = null,
)

fun TextView.apply(info: TextInfo?) {
    populate(info?.source)
    info?.colorResId?.let {
        setTextColor(color(it))
    }
}