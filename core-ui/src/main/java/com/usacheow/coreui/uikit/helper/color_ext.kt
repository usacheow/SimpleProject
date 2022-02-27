package com.usacheow.coreui.uikit.helper

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.usacheow.core.resource.ColorSource
import com.google.android.material.R as MaterialR
import com.usacheow.coreui.R as CoreUiR
import android.R as AndroidR

fun ColorSource.getColorInt(context: Context) = when (this) {
    is ColorSource.Simple -> color
    is ColorSource.Res -> ContextCompat.getColor(context, res)
    is ColorSource.Attr -> context.colorByAttr(res)
}

object ThemeColorsAttrs {
    val primary get() = MaterialR.attr.colorPrimary
    val onPrimary get() = MaterialR.attr.colorOnPrimary
    val primaryContainer get() = MaterialR.attr.colorPrimaryContainer
    val onPrimaryContainer get() = MaterialR.attr.colorOnPrimaryContainer
    val primaryInverse get() = MaterialR.attr.colorPrimaryInverse

    val secondary get() = MaterialR.attr.colorSecondary
    val onSecondary get() = MaterialR.attr.colorOnSecondary
    val secondaryContainer get() = MaterialR.attr.colorSecondaryContainer
    val onSecondaryContainer get() = MaterialR.attr.colorOnSecondaryContainer

    val tertiary get() = MaterialR.attr.colorTertiary
    val onTertiary get() = MaterialR.attr.colorOnTertiary
    val tertiaryContainer get() = MaterialR.attr.colorTertiaryContainer
    val onTertiaryContainer get() = MaterialR.attr.colorOnTertiaryContainer

    val error get() = MaterialR.attr.colorError
    val onError get() = MaterialR.attr.colorOnError
    val errorContainer get() = MaterialR.attr.colorErrorContainer
    val onErrorContainer get() = MaterialR.attr.colorOnErrorContainer

    val outline get() = MaterialR.attr.colorOutline
    val shimmer get() = CoreUiR.attr.colorShimmer

    val background get() = AndroidR.attr.colorBackground
    val onBackground get() = MaterialR.attr.colorOnBackground
    val surface get() = MaterialR.attr.colorSurface
    val onSurface get() = MaterialR.attr.colorOnSurface
    val surfaceVariant get() = MaterialR.attr.colorSurfaceVariant
    val onSurfaceVariant get() = MaterialR.attr.colorOnSurfaceVariant
    val surfaceInverse get() = MaterialR.attr.colorSurfaceInverse
    val onSurfaceInverse get() = MaterialR.attr.colorOnSurfaceInverse

    val symbolPrimary get() = CoreUiR.attr.colorSymbolPrimary
    val symbolPrimaryInverse get() = CoreUiR.attr.colorSymbolPrimaryInverse
    val symbolSecondary get() = CoreUiR.attr.colorSymbolSecondary
    val symbolSecondaryInverse get() = CoreUiR.attr.colorSymbolSecondaryInverse
    val symbolTertiary get() = CoreUiR.attr.colorSymbolTertiary
    val symbolTertiaryInverse get() = CoreUiR.attr.colorSymbolTertiaryInverse
}