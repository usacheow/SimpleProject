package com.usacheow.corecommon.container

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector

sealed class IconValue(val value: Any?) {

    class ResVector(@DrawableRes value: Int) : IconValue(value)

    class Vector(value: ImageVector) : IconValue(value)

    fun toImageValue() = when (this) {
        is ResVector -> ImageValue.ResVector(value as Int)
        is Vector -> ImageValue.Vector(value as ImageVector)
    }
}