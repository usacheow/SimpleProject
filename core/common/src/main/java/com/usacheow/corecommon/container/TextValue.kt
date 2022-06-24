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

fun String.textValue(): TextValue = TextValue.Simple(this)

fun AnnotatedString.textValue(): TextValue = TextValue.Annotated(this)

fun @receiver:StringRes Int.textValue(vararg args: Any): TextValue =
    TextValue.Res(value = this, args = args.toList())

fun @receiver:PluralsRes Int.textValue(quantity: Int, vararg args: Any): TextValue =
    TextValue.Plural(value = this, quantity = quantity, args = args.toList().ifEmpty { listOf(quantity) })