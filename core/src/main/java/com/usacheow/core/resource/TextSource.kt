package com.usacheow.core.resource

import android.content.Context
import android.os.Parcelable
import android.text.SpannedString
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.core.text.HtmlCompat
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
        val args: List<Any> = emptyList(),
    ) : TextSource()

    data class Plural(
        @PluralsRes val res: Int,
        val quantity: Int,
    ) : TextSource()

    data class FormattedPlural(
        @PluralsRes val res: Int,
        val quantity: Int,
        val args: List<Any> = listOf(quantity),
    ) : TextSource()

    fun toCharSequence(resources: ResourcesWrapper): CharSequence = when (this) {
        is Simple -> text

        is Spanned -> text

        is Html -> HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_LEGACY)

        is Res -> resources.getString(res, *args.toTypedArray())

        is Plural -> resources.getPluralString(res, quantity)

        is FormattedPlural -> resources.getPluralString(res, quantity).format(*args.toTypedArray())
    }

    fun toCharSequence(context: Context): CharSequence = toCharSequence(ResourcesWrapperImpl(context))
}

fun String.toTextSource() = TextSource.Simple(this)

fun SpannedString.toTextSource() = TextSource.Spanned(this)

fun Int.toTextSource() = TextSource.Res(this)

fun Int.toTextSource(quantity: Int) = TextSource.Plural(this, quantity)