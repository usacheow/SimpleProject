package com.usacheow.coreuiview.helper

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.DrawableCompat
import com.bumptech.glide.Glide
import com.usacheow.corecommon.container.ImageSource
import com.usacheow.corecommon.container.ColorSource
import com.usacheow.corecommon.container.CombineIcon

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

fun ImageSource.Res.get(context: Context) = context.colorifyIcon(res, color)

fun Context.colorifyIcon(@DrawableRes iconRes: Int, color: ColorSource?): Drawable? {
    val drawable = drawable(iconRes) ?: return null
    color ?: return drawable

    return DrawableCompat.wrap(drawable).mutate().apply {
        DrawableCompat.setTint(this, color.get(this@colorifyIcon))
    }
}