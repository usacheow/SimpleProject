package com.usacheow.coreui.compose.tools

import android.graphics.Bitmap
import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import coil.compose.rememberImagePainter

sealed class ImageValue(val value: Any?) {

    object Empty : ImageValue(null)

    class Url(value: String) : ImageValue(value)

    class Image(value: Bitmap) : ImageValue(value)

    class ResImage(@DrawableRes value: Int) : ImageValue(value)

    class ResVector(@DrawableRes value: Int) : ImageValue(value)

    class Vector(value: ImageVector) : ImageValue(value)

    @Composable
    fun get(): Painter? = when (this) {
        is Url, is Image, is ResImage -> rememberImagePainter(
            data = value,
            builder = {
                crossfade(true)
            }
        )

        is ResVector -> rememberVectorPainter(image = ImageVector.vectorResource(id = value as Int))

        is Vector -> rememberVectorPainter(image = value as ImageVector)

        else -> null
    }
}