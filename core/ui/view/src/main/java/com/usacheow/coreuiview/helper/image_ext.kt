package com.usacheow.coreuiview.helper

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.DrawableCompat
import com.bumptech.glide.Glide
import com.usacheow.corecommon.resource.ImageSource
import com.usacheow.corecommon.resource.ColorSource
import com.usacheow.corecommon.resource.CombineIcon

fun ImageView.populate(source: ImageSource?) {
    fun ImageView.load(url: String?, defaultIcon: Drawable? = null) {
        url ?: return
        Glide.with(this)
            .load(url)
            .placeholder(defaultIcon)
            .into(this)
    }

    when (source) {
        null -> makeGone()

        is ImageSource.Url -> load(source.url, source.defaultIcon?.getDrawable(context))

        is ImageSource.Image -> setImageBitmap(source.bitmap)

        is ImageSource.Res -> setImageDrawable(source.getDrawable(context))

        is ImageSource.Empty -> setImageDrawable(null)
    }
}

fun ImageView.populate(icon: CombineIcon?) {
    if (icon == null) {
        makeGone()
        return
    }
    makeVisible()
    populate(icon.icon)
    background = icon.background?.getDrawable(context)
}

fun ImageSource.Res.getDrawable(context: Context) = context.colorifyIcon(res, color)

fun Context.colorifyIcon(@DrawableRes iconRes: Int, color: ColorSource?): Drawable? {
    val drawable = drawable(iconRes) ?: return null
    color ?: return drawable

    return DrawableCompat.wrap(drawable).mutate().apply {
        DrawableCompat.setTint(this, color.getColorInt(this@colorifyIcon))
    }
}