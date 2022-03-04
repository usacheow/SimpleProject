package com.usacheow.coreuiview.helper

import android.content.Context
import android.widget.TextView
import androidx.core.text.HtmlCompat
import com.usacheow.corecommon.container.TextSource
import com.usacheow.coreuiview.resourcewrapper.ResourcesWrapper
import com.usacheow.coreuiview.resourcewrapper.ResourcesWrapperImpl

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

fun TextSource.get(resources: ResourcesWrapper): CharSequence = when (this) {
    is TextSource.Simple -> text

    is TextSource.Spanned -> text

    is TextSource.Html -> HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY)

    is TextSource.Res -> resources.getString(res, *args.toTypedArray())

    is TextSource.Plural -> resources.getPluralString(res, quantity)

    is TextSource.FormattedPlural -> resources.getPluralString(res, quantity).format(*args.toTypedArray())
}

fun TextSource.get(context: Context): CharSequence = get(ResourcesWrapperImpl(context))