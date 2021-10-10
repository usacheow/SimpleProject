package com.usacheow.core

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes

sealed class ImageSource {

    data class Url(
        val url: String,
    ) : ImageSource()

    data class Vector(
        val drawable: Drawable,
    ) : ImageSource()

    data class Image(
        val bitmap: Bitmap,
    ) : ImageSource()

    data class Res(
        @DrawableRes val res: Int,
    ) : ImageSource()

    object Empty : ImageSource()
}

fun String.toImageSource() = ImageSource.Url(this)
fun Int.toImageSource() = ImageSource.Res(this)