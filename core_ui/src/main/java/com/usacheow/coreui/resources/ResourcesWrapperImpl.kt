package com.usacheow.coreui.resources

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ResourcesWrapperImpl
@Inject constructor(
    @ApplicationContext private val context: Context
) : ResourcesWrapper {

    override fun getString(@StringRes id: Int) = context.getString(id)

    override fun getString(@StringRes id: Int, vararg formatArgs: Any) = context.getString(id, formatArgs)

    override fun getColor(@ColorRes id: Int) = ContextCompat.getColor(context, id)

    override fun getDrawable(@DrawableRes id: Int) = ContextCompat.getDrawable(context, id)

    override fun getDimen(@DimenRes id: Int) = context.resources.getDimension(id)

    override fun getAssets() = context.resources.assets

}
