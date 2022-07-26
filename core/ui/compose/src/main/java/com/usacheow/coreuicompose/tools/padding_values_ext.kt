package com.usacheow.coreuicompose.tools

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun PaddingValues.add(
    top: Dp = 0.dp,
    bottom: Dp = 0.dp,
    start: Dp = 0.dp,
    end: Dp = 0.dp,
) = PaddingValues(
    top = calculateTopPadding() + top,
    bottom = calculateBottomPadding() + bottom,
    start = calculateStartPadding(LocalLayoutDirection.current) + start,
    end = calculateEndPadding(LocalLayoutDirection.current) + end,
)

@Composable
fun PaddingValues.add(
    vertical: Dp = 0.dp,
    horizontal: Dp = 0.dp,
) = PaddingValues(
    top = calculateTopPadding() + vertical,
    bottom = calculateBottomPadding() + vertical,
    start = calculateStartPadding(LocalLayoutDirection.current) + horizontal,
    end = calculateEndPadding(LocalLayoutDirection.current) + horizontal,
)

@Composable
fun PaddingValues.add(
    padding: PaddingValues,
) = PaddingValues(
    top = calculateTopPadding() + padding.calculateTopPadding(),
    bottom = calculateBottomPadding() + padding.calculateBottomPadding(),
    start = calculateStartPadding(LocalLayoutDirection.current) + padding.calculateStartPadding(LocalLayoutDirection.current),
    end = calculateEndPadding(LocalLayoutDirection.current) + padding.calculateEndPadding(LocalLayoutDirection.current),
)