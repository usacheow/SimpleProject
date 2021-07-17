package com.usacheow.coreui.utils

import android.text.SpannedString
import android.widget.TextView
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.core.text.HtmlCompat
import com.usacheow.coreui.utils.view.makeGone
import com.usacheow.coreui.utils.view.makeVisible
import com.usacheow.coreui.utils.view.string

sealed class TextSource

data class TextString(
    val text: String,
) : TextSource()

data class TextSpanned(
    val text: SpannedString,
) : TextSource()

data class TextHtml(
    val html: String,
) : TextSource()

data class TextRes(
    @StringRes val res: Int,
) : TextSource()

data class TextPlural(
    @PluralsRes val res: Int,
    val quantity: Int,
) : TextSource()

fun TextView.populate(source: TextSource?) {
    fun TextView.populate(s: CharSequence) {
        if (s.isEmpty()) {
            makeGone()
        } else {
            text = s
            makeVisible()
        }
    }

    when (source) {
        null -> makeGone()

        is TextString -> populate(source.text)

        is TextSpanned -> populate(source.text)

        is TextHtml -> {
            text = HtmlCompat.fromHtml(source.html, HtmlCompat.FROM_HTML_MODE_LEGACY)
            makeVisible()
        }

        is TextRes -> {
            text = string(source.res)
            makeVisible()
        }

        is TextPlural -> {
            text = resources.getQuantityText(source.res, source.quantity)
            makeVisible()
        }
    }
}