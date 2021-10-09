package com.usacheow.coreui.utils.view

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.usacheow.core.ImageSource

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