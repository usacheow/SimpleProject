package com.usacheow.corecommon.container

import androidx.compose.ui.text.AnnotatedString
import com.usacheow.corecommon.strings.StringKey

sealed class TextValue {

    data class Simple(
        val value: String,
    ) : TextValue()

    data class Annotated(
        val value: AnnotatedString,
    ) : TextValue()

    data class Res(
        val value: StringKey,
        val args: List<Any> = emptyList(),
    ) : TextValue()

    data class Plural(
        val value: StringKey,
        val quantity: Int,
        val args: List<Any> = listOf(quantity),
    ) : TextValue()
}

fun String.textValue(): TextValue = TextValue.Simple(this)

fun AnnotatedString.textValue(): TextValue = TextValue.Annotated(this)

fun StringKey.textValue(vararg args: Any): TextValue =
    TextValue.Res(value = this, args = args.toList())

fun StringKey.textValue(quantity: Int, vararg args: Any): TextValue =
    TextValue.Plural(value = this, quantity = quantity, args = args.toList().ifEmpty { listOf(quantity) })