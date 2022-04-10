package com.usacheow.corecommon.container

import android.os.Parcelable
import android.text.SpannedString
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import kotlinx.parcelize.Parcelize

sealed class TextSource {

    @Parcelize
    data class Simple(
        val text: String,
    ) : TextSource(), Parcelable

    data class Spanned(
        val text: SpannedString,
    ) : TextSource()

    data class Res(
        @StringRes val res: Int,
        val args: List<Any> = emptyList(),
    ) : TextSource()

    data class Plural(
        @PluralsRes val res: Int,
        val quantity: Int,
        val args: List<Any> = listOf(quantity),
    ) : TextSource()
}

fun String.toTextSource() = TextSource.Simple(this)