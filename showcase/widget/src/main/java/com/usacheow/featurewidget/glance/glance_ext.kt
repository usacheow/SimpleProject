package com.usacheow.featurewidget.glance

import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.glance.GlanceModifier
import androidx.glance.LocalContext
import androidx.glance.appwidget.appWidgetBackground
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.height
import androidx.glance.unit.ColorProvider
import androidx.glance.unit.Dimension

val Float.toPx get() = this * Resources.getSystem().displayMetrics.density

@Composable
fun appWidgetBackgroundModifier(
    backgroundColor: ColorProvider = GlanceTheme.colors.background,
    radius: Dimension = GlanceTheme.values.widgetLargeRadius,
) = GlanceModifier
    .fillMaxSize()
    .appWidgetBackground()
    .cornerRadius(radius)
    .background(backgroundColor)

fun GlanceModifier.cornerRadius(dimen: Dimension) = when (dimen) {
    is Dimension.Dp -> cornerRadius(dimen.dp)
    is Dimension.Resource -> cornerRadius(dimen.res)
    else -> this
}

fun GlanceModifier.height(dimen: Dimension) = when (dimen) {
    is Dimension.Dp -> height(dimen.dp)
    is Dimension.Resource -> height(dimen.res)
    else -> this
}

@Composable
fun stringResource(@StringRes id: Int, vararg args: Any): String {
    return LocalContext.current.getString(id, args)
}