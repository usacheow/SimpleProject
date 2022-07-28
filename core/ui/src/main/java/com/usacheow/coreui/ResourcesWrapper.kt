package com.usacheow.coreui

import android.content.Context
import android.content.res.AssetManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import androidx.annotation.ArrayRes
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.usacheow.corecommon.container.TextValue
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

interface ResourcesWrapper {

    fun getString(@StringRes id: Int): String

    fun getString(@StringRes id: Int, vararg formatArgs: Any): String

    fun getPluralString(@PluralsRes id: Int, quantity: Int): String

    fun getStringArray(@ArrayRes id: Int): Array<String>

    fun getColor(@ColorRes id: Int): Int

    fun getDrawable(@DrawableRes id: Int): Drawable?

    fun getBitmap(@DrawableRes id: Int): Bitmap?

    fun getDimen(@DimenRes id: Int): Float

    fun getAssets(): AssetManager
}

class ResourcesWrapperImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : ResourcesWrapper {

    override fun getString(@StringRes id: Int) = context.getString(id)

    override fun getString(@StringRes id: Int, vararg formatArgs: Any) = context.getString(id, *formatArgs)

    override fun getPluralString(@PluralsRes id: Int, quantity: Int) = context.resources.getQuantityString(id, quantity)

    override fun getStringArray(@ArrayRes id: Int) = context.resources.getStringArray(id)

    override fun getColor(@ColorRes id: Int) = ContextCompat.getColor(context, id)

    override fun getDrawable(@DrawableRes id: Int) = ContextCompat.getDrawable(context, id)

    override fun getBitmap(id: Int): Bitmap? = BitmapFactory.decodeResource(context.resources, id)

    override fun getDimen(@DimenRes id: Int) = context.resources.getDimension(id)

    override fun getAssets() = context.resources.assets
}

fun TextValue.mapToString(resourcesWrapper: ResourcesWrapper): String = when (this) {
    is TextValue.Simple -> value

    is TextValue.Annotated -> value.text

    is TextValue.Res -> resourcesWrapper.getString(value, *args.toTypedArray())

    is TextValue.Plural -> resourcesWrapper.getPluralString(value, quantity).format(*args.toTypedArray())
}

@Module
@InstallIn(SingletonComponent::class)
interface ResourcesWrapperModule {

    @Binds
    @Singleton
    fun resources(resources: ResourcesWrapperImpl): ResourcesWrapper
}