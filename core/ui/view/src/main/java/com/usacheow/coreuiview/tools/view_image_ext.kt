package com.usacheow.coreuiview.tools

import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.usacheow.corecommon.container.CombineIcon
import com.usacheow.corecommon.container.ImageSource
import com.usacheow.coreuiview.tools.resource.get

fun ImageView.populate(source: ImageSource?) {
    when (source) {
        null -> makeGone()

        is ImageSource.Url -> load(source.url, source.defaultIcon?.get(context))

        is ImageSource.Image -> setImageBitmap(source.bitmap)

        is ImageSource.Res -> setImageDrawable(source.get(context))

        is ImageSource.Empty -> setImageDrawable(null)
    }
}

fun ImageView.load(url: String?, defaultIcon: Drawable? = null) {
    url ?: return
    Glide.with(this)
        .load(url)
        .placeholder(defaultIcon)
        .into(this)
}

fun ImageView.populate(icon: CombineIcon?) {
    if (icon == null) {
        makeGone()
        return
    }
    makeVisible()
    populate(icon.icon)
    background = icon.background?.get(context)
}