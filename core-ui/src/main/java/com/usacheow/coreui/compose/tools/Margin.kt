package com.usacheow.coreui.compose.tools

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class Margin {

    object Empty : Margin()

    data class Acis(
        val vertical: Dp = 0.dp,
        val horizontal: Dp = 0.dp,
    ) : Margin()

    data class Vertical(
        val top: Dp = 0.dp,
        val bottom: Dp = 0.dp,
    ) : Margin()

    data class Horizontal(
        val start: Dp = 0.dp,
        val end: Dp = 0.dp,
    ) : Margin()

    data class All(
        val top: Dp = 0.dp,
        val bottom: Dp = 0.dp,
        val start: Dp = 0.dp,
        val end: Dp = 0.dp,
    ) : Margin()

    data class Top(
        val top: Dp = 0.dp,
    ) : Margin()

    data class Bottom(
        val bottom: Dp = 0.dp,
    ) : Margin()

    data class Left(
        val start: Dp = 0.dp,
    ) : Margin()

    data class Right(
        val end: Dp = 0.dp,
    ) : Margin()
}