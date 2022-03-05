package com.usacheow.coreuiview.tools.resource

import android.content.Context
import androidx.core.text.HtmlCompat
import com.usacheow.corecommon.container.TextSource

fun TextSource.get(resources: ResourcesWrapper): CharSequence = when (this) {
    is TextSource.Simple -> text

    is TextSource.Spanned -> text

    is TextSource.Html -> HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY)

    is TextSource.Res -> resources.getString(res, *args.toTypedArray())

    is TextSource.Plural -> resources.getPluralString(res, quantity)

    is TextSource.FormattedPlural -> resources.getPluralString(res, quantity).format(*args.toTypedArray())
}

fun TextSource.get(context: Context): CharSequence = get(ResourcesWrapperImpl(context))