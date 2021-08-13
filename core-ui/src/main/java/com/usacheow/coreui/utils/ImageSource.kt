package com.usacheow.coreui.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.usacheow.coreui.utils.view.makeGone

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

fun ImageView.populate(source: ImageSource?) {
    fun ImageView.load(url: String?) {
        url ?: return
        Glide.with(this).load(url).into(this)
    }

    when (source) {
        null -> makeGone()

        is ImageSource.Url -> load(source.url)

        is ImageSource.Vector -> setImageDrawable(source.drawable)

        is ImageSource.Image -> setImageBitmap(source.bitmap)

        is ImageSource.Res -> setImageResource(source.res)

        is ImageSource.Empty -> setImageDrawable(null)
    }
}