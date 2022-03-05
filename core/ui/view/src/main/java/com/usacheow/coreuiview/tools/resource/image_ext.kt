package com.usacheow.coreuiview.tools.resource

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.graphics.drawable.DrawableCompat
import com.usacheow.corecommon.container.ColorSource
import com.usacheow.corecommon.container.ImageSource

fun ImageSource.Res.get(context: Context) = context.colorifyIcon(res, color)

fun Context.colorifyIcon(@DrawableRes iconRes: Int, color: ColorSource?): Drawable? {
    val drawable = drawable(iconRes) ?: return null
    color ?: return drawable

    return DrawableCompat.wrap(drawable).mutate().apply {
        DrawableCompat.setTint(this, color.get(this@colorifyIcon))
    }
}