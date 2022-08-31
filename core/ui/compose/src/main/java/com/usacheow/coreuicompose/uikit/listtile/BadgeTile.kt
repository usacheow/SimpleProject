package com.usacheow.coreuicompose.uikit.listtile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.TileState
import com.usacheow.coreuicompose.tools.get
import com.usacheow.coreuicompose.uikit.other.ShimmerTileLine
import com.usacheow.coreuitheme.compose.AppTheme

sealed class BadgeTileState : TileState {

    data class Data(
        val header: TextValue? = null,
        val value: TextValue,
        val contentColor: Color? = null,
        val containerColor: Color? = null,
        val onClick: (() -> Unit)? = null,
    ) : BadgeTileState()

    data class Shimmer(
        val hasHeader: Boolean = true,
    ) : BadgeTileState()

    @Composable
    override fun Content(modifier: Modifier) {
        BadgeTile(modifier, this)
    }
}

object BadgeTileConfig {

    val LinesMinWidth = 120.dp
    val LinesMaxWidth = 156.dp
    val LinesBetweenPadding = 8.dp

    @Composable
    fun colors(
        contentColor: Color,
        containerColor: Color,
    ) = CardDefaults.cardColors(
        containerColor = containerColor,
        contentColor = contentColor,
    )

    @Composable
    fun elevation() = CardDefaults.elevatedCardElevation(
        defaultElevation = 8.dp,
        pressedElevation = 8.dp,
        focusedElevation = 8.dp,
        hoveredElevation = 8.dp,
        draggedElevation = 8.dp,
    )

    @Composable
    fun shape() = AppTheme.shapes.large

    @Composable
    fun headerStyle() = AppTheme.specificTypography.bodyMedium

    @Composable
    fun valueStyle() = AppTheme.specificTypography.bodyLarge

    @Composable
    fun defaultContentColor() = AppTheme.specificColorScheme.onSurface

    @Composable
    fun defaultContainerColor() = AppTheme.specificColorScheme.surface
}

@Composable
fun BadgeTile(
    modifier: Modifier = Modifier,
    data: BadgeTileState,
) {
    when (data) {
        is BadgeTileState.Data -> Data(modifier, data)
        is BadgeTileState.Shimmer -> Shimmer(modifier, data)
    }
}

@Composable
private fun Data(
    modifier: Modifier = Modifier,
    data: BadgeTileState.Data,
) {
    BadgeTileContainer(
        modifier = modifier,
        contentColor = data.contentColor ?: BadgeTileConfig.defaultContentColor(),
        containerColor = data.containerColor ?: BadgeTileConfig.defaultContainerColor(),
        onClick = data.onClick,
    ) {
        data.header?.get()?.let {
            Text(
                text = it,
                style = BadgeTileConfig.headerStyle(),
                maxLines = 1,
                modifier = Modifier
                    .padding(bottom = BadgeTileConfig.LinesBetweenPadding)
                    .widthIn(min = BadgeTileConfig.LinesMinWidth, max = BadgeTileConfig.LinesMaxWidth),
            )
        }
        Box {
            Text(
                text = "\n\n",
                style = BadgeTileConfig.valueStyle(),
                maxLines = 2,
                modifier = Modifier
                    .alpha(0f)
                    .widthIn(min = BadgeTileConfig.LinesMinWidth, max = BadgeTileConfig.LinesMaxWidth),
            )
            Text(
                text = data.value.get(),
                style = BadgeTileConfig.valueStyle(),
                maxLines = 2,
                modifier = Modifier.widthIn(min = BadgeTileConfig.LinesMinWidth, max = BadgeTileConfig.LinesMaxWidth),
            )
        }
    }
}

@Composable
private fun Shimmer(
    modifier: Modifier = Modifier,
    data: BadgeTileState.Shimmer,
) {
    BadgeTileContainer(
        modifier = modifier,
        contentColor = BadgeTileConfig.defaultContentColor(),
        containerColor = BadgeTileConfig.defaultContainerColor(),
        onClick = null,
    ) {
        if (data.hasHeader) {
            ShimmerTileLine(
                width = BadgeTileConfig.LinesMinWidth,
                height = BadgeTileConfig.headerStyle().lineHeight.value.dp,
            )
            Spacer(modifier = Modifier.height(BadgeTileConfig.LinesBetweenPadding))
        }
        ShimmerTileLine(
            width = BadgeTileConfig.LinesMaxWidth,
            height = BadgeTileConfig.valueStyle().lineHeight.value.dp.times(2) + 5.dp,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BadgeTileContainer(
    modifier: Modifier = Modifier,
    contentColor: Color,
    containerColor: Color,
    onClick: (() -> Unit)?,
    content: @Composable () -> Unit,
) {
    val body: @Composable ColumnScope.() -> Unit = {
        Column(modifier = Modifier.padding(AppTheme.specificValues.default_padding)) {
            content()
        }
    }
    if (onClick != null) {
        Card(
            modifier = modifier,
            shape = BadgeTileConfig.shape(),
            colors = BadgeTileConfig.colors(
                containerColor = containerColor,
                contentColor = contentColor,
            ),
            elevation = BadgeTileConfig.elevation(),
            onClick = onClick,
            content = body,
        )
    } else {
        Card(
            modifier = modifier,
            shape = BadgeTileConfig.shape(),
            colors = BadgeTileConfig.colors(
                containerColor = containerColor,
                contentColor = contentColor,
            ),
            elevation = BadgeTileConfig.elevation(),
            content = body,
        )
    }
}