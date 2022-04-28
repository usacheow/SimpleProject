package com.usacheow.corecommon.container

import androidx.annotation.ColorRes
import androidx.compose.ui.graphics.Color

sealed class ColorValue {

    data class Simple(
        val color: Color,
    ) : ColorValue()

    data class Res(
        @ColorRes val res: Int,
    ) : ColorValue()

    companion object Factory {

        @Throws
        fun fromHex(hex: String): ColorValue {
            var hexCode = hex.replace("#", "")
            if (hexCode.length == 6) {
                hexCode = "FF$hexCode"
            }
            return Simple(Color("0x$hexCode".toLong()))
        }

        fun fromColor(color: Color) = Simple(color)

        fun fromRes(@ColorRes res: Int) = Res(res)
    }
}