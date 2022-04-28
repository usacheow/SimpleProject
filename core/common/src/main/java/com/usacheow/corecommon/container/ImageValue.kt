package com.usacheow.corecommon.container

import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector

sealed class ImageValue(val value: Any?) {

    class Url(value: String) : ImageValue(value)

    class Image(value: Bitmap) : ImageValue(value)

    class ResImage(@DrawableRes value: Int) : ImageValue(value)

    class ResVector(@DrawableRes value: Int) : ImageValue(value)

    class Vector(value: ImageVector) : ImageValue(value)
}