package com.usacheow.corecommon.container

import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.compose.ui.text.AnnotatedString

sealed class TextValue {

    data class Simple(
        val value: String,
    ) : TextValue()

    data class Annotated(
        val value: AnnotatedString,
    ) : TextValue()

    data class Res(
        @StringRes val value: Int,
        val args: List<Any> = emptyList(),
    ) : TextValue()

    data class Plural(
        @PluralsRes val value: Int,
        val quantity: Int,
        val args: List<Any> = listOf(quantity),
    ) : TextValue()
}