package com.usacheow.corecommon.container.compose

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

        fun fromColor(color: Color) = Simple(color)

        fun fromRes(@ColorRes res: Int) = Res(res)
    }
}
