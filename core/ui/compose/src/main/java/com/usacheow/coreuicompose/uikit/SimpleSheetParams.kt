package com.usacheow.coreuicompose.uikit

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetState
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import com.usacheow.coreuicompose.tools.insetBottom
import com.usacheow.coreuicompose.tools.insetTop
import com.usacheow.coreuitheme.compose.AppTheme

class SimpleSheetParams(
    val swipePercent: Float,
    val sheetContentPadding: PaddingValues,
    val sheetShape: Shape,
)

object SheetConfig {

    val DefaultRadius = AppTheme.specificValues.radius_extra_large

    fun sheetContentPadding(
        swipePercent: Float,
        maxTopPadding: Dp,
        bottomPadding: Dp,
    ) = PaddingValues(
        top = maxTopPadding * swipePercent,
        bottom = bottomPadding,
    )
    fun currentRadius(
        swipePercent: Float,
        borderValue: Float,
    ) = (DefaultRadius * radiusCoefficient(swipePercent, borderValue)).run {
        RoundedCornerShape(topStart = this, topEnd = this)
    }

    private fun radiusCoefficient(swipePercent: Float, borderValue: Float) = when {
        swipePercent < borderValue -> 1f
        else -> 1f - (swipePercent - borderValue) / (1f - borderValue)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun simpleSheetParams(
    sheetState: BottomSheetState,
    borderValue: Float = .8f,
    maxTopPadding: Dp = insetTop().asPaddingValues().calculateTopPadding(),
    bottomPadding: Dp = insetBottom().asPaddingValues().calculateBottomPadding(),
) = SimpleSheetParams(
    swipePercent = sheetState.swipePercent(),
    sheetContentPadding = SheetConfig.sheetContentPadding(sheetState.swipePercent(), maxTopPadding, bottomPadding),
    sheetShape = SheetConfig.currentRadius(sheetState.swipePercent(), borderValue),
)

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun simpleSheetParams(
    sheetState: ModalBottomSheetState,
    borderValue: Float = .8f,
    maxTopPadding: Dp = insetTop().asPaddingValues().calculateTopPadding(),
    bottomPadding: Dp = insetBottom().asPaddingValues().calculateBottomPadding(),
) = SimpleSheetParams(
    swipePercent = sheetState.swipePercent(),
    sheetContentPadding = SheetConfig.sheetContentPadding(sheetState.swipePercent(), maxTopPadding, bottomPadding),
    sheetShape = SheetConfig.currentRadius(sheetState.swipePercent(), borderValue),
)

@OptIn(ExperimentalMaterialApi::class)
private fun BottomSheetState.swipePercent(): Float {
    return when (SwipeDirection.from(direction = direction, offset = offset.value)) {
        SwipeDirection.ToTop -> progress.fraction
        SwipeDirection.ToBottom -> 1f - progress.fraction
        SwipeDirection.None -> when (currentValue) {
            BottomSheetValue.Collapsed -> 0f
            BottomSheetValue.Expanded -> 1f
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
private fun ModalBottomSheetState.swipePercent(): Float {
    return when (SwipeDirection.from(direction = direction, offset = offset.value)) {
        SwipeDirection.ToTop -> progress.fraction.run {
            when {
                currentValue == ModalBottomSheetValue.HalfExpanded -> this / 2f + .5f
                targetValue == ModalBottomSheetValue.HalfExpanded -> this / 2f
                else -> this
            }
        }
        SwipeDirection.ToBottom -> (1f - progress.fraction).run {
            when {
                currentValue == ModalBottomSheetValue.HalfExpanded -> this / 2f
                targetValue == ModalBottomSheetValue.HalfExpanded -> this / 2f + .5f
                else -> this
            }
        }
        SwipeDirection.None -> when (currentValue) {
            ModalBottomSheetValue.Hidden -> 0f
            ModalBottomSheetValue.HalfExpanded -> .5f
            ModalBottomSheetValue.Expanded -> 1f
        }
    }
}

private enum class SwipeDirection {

    ToTop, ToBottom, None;

    companion object {

        fun from(direction: Float, offset: Float) = when {
            direction == -1f || offset < 10f -> ToTop
            direction == 1f -> ToBottom
            else -> None
        }
    }
}