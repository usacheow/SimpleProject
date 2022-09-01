package com.usacheow.featurewidget.glance

import android.R
import android.os.Build
import androidx.compose.ui.unit.dp
import androidx.glance.unit.Dimension

internal val DefaultGlanceValues = GlanceValues(
    widgetLargeRadius = when {
        Build.VERSION.SDK_INT >= 31 -> Dimension.Resource(R.dimen.system_app_widget_background_radius)
        else -> Dimension.Dp(16.dp)
    },
    innerLargeRadius = when {
        Build.VERSION.SDK_INT >= 31 -> Dimension.Resource(R.dimen.system_app_widget_inner_radius)
        else -> Dimension.Dp(8.dp)
    },
    innerMediumRadius = Dimension.Dp(6.dp),
    innerSmallRadius = Dimension.Dp(4.dp),

    largeContentPadding = Dimension.Dp(16.dp),
    mediumContentPadding = Dimension.Dp(8.dp),
    smallContentPadding = Dimension.Dp(4.dp),
)

data class GlanceValues(
    val widgetLargeRadius: Dimension,
    val innerLargeRadius: Dimension,
    val innerMediumRadius: Dimension,
    val innerSmallRadius: Dimension,

    val largeContentPadding: Dimension.Dp,
    val mediumContentPadding: Dimension.Dp,
    val smallContentPadding: Dimension.Dp,
)