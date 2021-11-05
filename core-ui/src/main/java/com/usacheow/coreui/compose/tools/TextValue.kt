package com.usacheow.coreui.compose.tools

import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString

sealed class TextValue {

    object Empty : TextValue()

    data class Simple(
        val value: String,
    ) : TextValue()

    data class Annotated(
        val value: AnnotatedString,
    ) : TextValue()

    data class Html(
        val value: String,
    ) : TextValue()

    data class Res(
        @StringRes val value: Int,
        val args: List<Any>,
    ) : TextValue()

    data class Plural(
        @PluralsRes val value: Int,
        val quantity: Int,
    ) : TextValue()

    @Composable
    fun get() = when (this) {
        is Simple -> AnnotatedString(value)

        is Annotated -> value

        is Html -> value.parseHtml()

        is Res -> AnnotatedString(string(value, *args.toTypedArray()))

        is Plural -> AnnotatedString(plural(value, quantity))

        is Empty -> AnnotatedString("")
    }
}