package com.usacheow.coreui

import android.content.Context
import android.content.res.AssetManager
import androidx.annotation.ArrayRes
import androidx.annotation.PluralsRes
import androidx.annotation.StringRes
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

    fun getAssets(): AssetManager
}

class ResourcesWrapperImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : ResourcesWrapper {

    override fun getString(@StringRes id: Int) = context.getString(id)

    override fun getString(@StringRes id: Int, vararg formatArgs: Any) = context.getString(id, *formatArgs)

    override fun getPluralString(@PluralsRes id: Int, quantity: Int) = context.resources.getQuantityString(id, quantity)

    override fun getStringArray(@ArrayRes id: Int) = context.resources.getStringArray(id)

    override fun getAssets() = context.resources.assets
}

@Module
@InstallIn(SingletonComponent::class)
interface ResourcesWrapperModule {

    @Binds
    @Singleton
    fun resources(resources: ResourcesWrapperImpl): ResourcesWrapper
}