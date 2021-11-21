package com.usacheow.core

import android.os.Parcelable
import android.text.SpannedString
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.core.text.HtmlCompat
import com.usacheow.core.resource.ResourcesWrapper
import kotlinx.parcelize.Parcelize

sealed class TextSource {

    @Parcelize
    data class Simple(
        val text: String,
    ) : TextSource(), Parcelable

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

    fun toCharSequence(resources: ResourcesWrapper): CharSequence? = when (this) {
        null -> null

        is Simple -> text

        is Spanned -> text

        is Html -> HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY)

        is Res -> resources.getString(res)

        is Plural -> resources.getPluralString(res, quantity)
    }
}

fun String.toTextSource() = TextSource.Simple(this)

fun SpannedString.toTextSource() = TextSource.Spanned(this)

fun Int.toTextSource() = TextSource.Res(this)

fun Int.toTextSource(quantity: Int) = TextSource.Plural(this, quantity)