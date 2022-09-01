package com.usacheow.coreuitheme.compose

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp
import com.usacheow.corecommon.strings.StringHolder

val LocalStringHolder = staticCompositionLocalOf { StringHolder() }

object SpecificValues {

    val ripple_inner_padding = 8.dp
    val ripple_outer_padding = 8.dp
    val default_padding = ripple_inner_padding + ripple_outer_padding

    val stroke_card = 1.dp

    val radius_extra_small = 4.dp
    val radius_small = 8.dp
    val radius_medium = 16.dp
    val radius_large = 20.dp
    val radius_extra_large = 24.dp

    val radius_0 = 0.dp
    val radius_2 = 2.dp
    val radius_4 = 4.dp
    val radius_8 = 8.dp
    val radius_12 = 12.dp
    val radius_16 = 16.dp

    val elevation_0 = 0.dp
    val elevation_4 = 4.dp
    val elevation_8 = 8.dp
    val elevation_16 = 16.dp
    val elevation_32 = 32.dp

    const val UnbreakableSpace = " "
}