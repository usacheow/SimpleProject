package com.usacheow.core.resource

import android.content.Context
import android.content.res.AssetManager
import android.graphics.drawable.Drawable
import androidx.annotation.ArrayRes
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

interface ResourcesWrapper {

    fun getString(@StringRes id: Int): String

    fun getString(@StringRes id: Int, vararg formatArgs: Any): String

    fun getPluralString(@PluralsRes id: Int, quantity: Int): String

    fun getStringArray(@ArrayRes id: Int): Array<String>

    fun getColor(@ColorRes id: Int): Int

    fun getDrawable(@DrawableRes id: Int): Drawable?

    fun getDimen(@DimenRes id: Int): Float

    fun getAssets(): AssetManager
}

class ResourcesWrapperImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : ResourcesWrapper {

    override fun getString(@StringRes id: Int) = context.getString(id)

    override fun getString(@StringRes id: Int, vararg formatArgs: Any) = context.getString(id, *formatArgs)

    override fun getPluralString(id: Int, quantity: Int) = context.resources.getQuantityString(id, quantity)

    override fun getStringArray(@ArrayRes id: Int) = context.resources.getStringArray(id)

    override fun getColor(@ColorRes id: Int) = ContextCompat.getColor(context, id)

    override fun getDrawable(@DrawableRes id: Int) = ContextCompat.getDrawable(context, id)

    override fun getDimen(@DimenRes id: Int) = context.resources.getDimension(id)

    override fun getAssets() = context.resources.assets
}