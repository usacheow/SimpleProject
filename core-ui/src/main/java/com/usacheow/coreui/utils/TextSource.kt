package com.usacheow.coreui.utils

import android.text.SpannedString
import android.widget.TextView
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.core.text.HtmlCompat
import com.usacheow.coreui.utils.view.makeGone
import com.usacheow.coreui.utils.view.makeVisible
import com.usacheow.coreui.utils.view.string

sealed class TextSource {

    data class Simple(
        val text: String,
    ) : TextSource()

    data class Spanned(
        val text: SpannedString,
    ) : TextSource()

    data class Html(
        val html: String,
    ) : TextSource()

    data class Res(
        @StringRes val res: Int,
    ) : TextSource()

    data class Plural(
        @PluralsRes val res: Int,
        val quantity: Int,
    ) : TextSource()
}

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

        is TextSource.Simple -> populate(source.text)

        is TextSource.Spanned -> populate(source.text)

        is TextSource.Html -> {
            text = HtmlCompat.fromHtml(source.html, HtmlCompat.FROM_HTML_MODE_LEGACY)
            makeVisible()
        }

        is TextSource.Res -> {
            text = string(source.res)
            makeVisible()
        }

        is TextSource.Plural -> {
            text = resources.getQuantityText(source.res, source.quantity)
            makeVisible()
        }
    }
}