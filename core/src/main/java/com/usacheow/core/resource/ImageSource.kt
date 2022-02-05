package com.usacheow.core.resource

import android.graphics.Bitmap
import androidx.annotation.DrawableRes

sealed class ImageSource {

    data class Url(
        val url: String,
        val defaultIcon: Res? = null,
    ) : ImageSource()

    data class Image(
        val bitmap: Bitmap,
    ) : ImageSource()

    data class Res(
        @DrawableRes val res: Int,
        val color: ColorSource? = null,
    ) : ImageSource()

    object Empty : ImageSource()
}

data class CombineIcon(
    val icon: ImageSource.Res,
    val background: ImageSource.Res?,
)

fun String.toImageSource() = ImageSource.Url(this)
fun Int.toImageSource() = ImageSource.Res(this)
fun Bitmap.toImageSource() = ImageSource.Image(this)