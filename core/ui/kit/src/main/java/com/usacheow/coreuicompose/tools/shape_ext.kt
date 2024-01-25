package com.usacheow.coreuicompose.tools

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun RoundedCornerShape(
    top: Dp = 0.dp,
    bottom: Dp = 0.dp,
) = androidx.compose.foundation.shape.RoundedCornerShape(
    topEnd = top,
    topStart = top,
    bottomEnd = bottom,
    bottomStart = bottom,
)

fun CornerBasedShape.clearTopValues() = copy(
    topEnd = CornerSize(0.dp),
    topStart = CornerSize(0.dp),
)

fun CornerBasedShape.clearBottomValues() = copy(
    bottomEnd = CornerSize(0.dp),
    bottomStart = CornerSize(0.dp),
)