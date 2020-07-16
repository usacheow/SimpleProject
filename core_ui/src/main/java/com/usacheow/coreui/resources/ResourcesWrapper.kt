package com.usacheow.coreui.resources

import android.content.res.AssetManager
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface ResourcesWrapper {

    fun getString(@StringRes id: Int): String

    fun getString(@StringRes id: Int, vararg formatArgs: Any): String

    fun getColor(@ColorRes id: Int): Int

    fun getDrawable(@DrawableRes id: Int): Drawable?

    fun getDimen(@DimenRes id: Int): Float

    fun getAssets(): AssetManager

}
