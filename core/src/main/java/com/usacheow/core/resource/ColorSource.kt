package com.usacheow.core.resource

import android.content.Context
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat

private const val HEX_PREFIX = '#'

sealed class ColorSource {

    data class Simple(
        @ColorInt val color: Int,
    ) : ColorSource()

    data class Res(
        @ColorRes val res: Int,
    ) : ColorSource()

    companion object Factory {

        @Throws
        fun fromHex(hex: String): ColorSource {
            val hexValue = when {
                hex.contains(HEX_PREFIX) -> hex
                else -> "$HEX_PREFIX$hex"
            }
            return Simple(Color.parseColor(hexValue))
        }

        fun fromColorInt(@ColorInt color: Int) = Simple(color)

        fun fromRes(@ColorRes res: Int) = Res(res)
    }

    fun getColorInt(context: Context) = when (this) {
        is Simple -> color
        is Res -> ContextCompat.getColor(context, res)
    }
}
