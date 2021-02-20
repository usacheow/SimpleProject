package com.usacheow.coreui.utils

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.usacheow.coreui.utils.view.makeGone

sealed class ImageSource

data class ImageUrl(
    val url: String,
) : ImageSource()

data class ImageDrawable(
    val drawable: Drawable,
) : ImageSource()

data class ImageBitmap(
    val bitmap: Bitmap,
) : ImageSource()

data class ImageRes(
    @DrawableRes val res: Int,
) : ImageSource()

object ImageEmpty : ImageSource()

fun ImageView.populate(source: ImageSource?) {
    fun ImageView.load(url: String?) {
        url ?: return
        Glide.with(this).load(url).into(this)
    }

    when (source) {
        null -> makeGone()

        is ImageUrl -> load(source.url)

        is ImageDrawable -> setImageDrawable(source.drawable)

        is ImageBitmap -> setImageBitmap(source.bitmap)

        is ImageRes -> setImageResource(source.res)

        is ImageEmpty -> setImageDrawable(null)
    }
}