package com.usacheow.coreuicompose.uikit

import android.content.res.Configuration
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.TilesShimmerState
import com.usacheow.coreuicompose.tools.TileState
import com.usacheow.coreuicompose.tools.get
import com.usacheow.coreuicompose.uikit.status.ShimmerTileLine
import com.usacheow.coreuitheme.compose.AppTheme
import com.usacheow.coreuitheme.compose.DimenValues
import com.usacheow.coreuitheme.compose.PreviewAppTheme

data class BadgeTileState(
    val header: TextValue? = null,
    val value: TextValue,
    val contentColor: Color? = null,
    val containerColor: Color? = null,
    val clickListener: (() -> Unit)? = null,
) : TileState {

    @Composable
    override fun Content(modifier: Modifier) {
        BadgeTile(modifier, this)
    }

    companion object {
        fun shimmer(hasHeader: Boolean = true) = TilesShimmerState {
            BadgeTileShimmer(it, hasHeader)
        }
    }
}

@Composable
fun BadgeTile(
    modifier: Modifier = Modifier,
    data: BadgeTileState,
) {
    BadgeTileContainer(
        modifier = modifier,
        contentColor = data.contentColor ?: AppTheme.specificColorScheme.onSurface,
        containerColor = data.containerColor ?: AppTheme.specificColorScheme.surface,
        clickListener = data.clickListener,
    ) {
        data.header?.get()?.let {
            Text(
                text = it,
                color = AppTheme.specificColorScheme.symbolPrimary,
                style = AppTheme.typography.bodyMedium,
                maxLines = 1,
                modifier = Modifier
                    .padding(bottom = BadgeTileConfig.LinesBetweenPadding)
                    .widthIn(min = BadgeTileConfig.LinesMinWidth, max = BadgeTileConfig.LinesMaxWidth),
            )
        }
        Text(
            text = data.value.get().plus(AnnotatedString("\n")),
            color = AppTheme.specificColorScheme.symbolPrimary,
            style = AppTheme.typography.bodyLarge,
            maxLines = 2,
            modifier = Modifier.widthIn(min = BadgeTileConfig.LinesMinWidth, max = BadgeTileConfig.LinesMaxWidth),
        )
    }
}

@Composable
fun BadgeTileShimmer(
    modifier: Modifier = Modifier,
    hasHeader: Boolean,
) {
    BadgeTileContainer(
        modifier = modifier,
        contentColor = AppTheme.specificColorScheme.symbolPrimary,
        containerColor = AppTheme.specificColorScheme.surface,
        clickListener = null,
    ) {
        if (hasHeader) {
            ShimmerTileLine(
                width = BadgeTileConfig.LinesMinWidth,
                height = AppTheme.typography.bodyMedium.lineHeight.value.dp,
            )
            Spacer(modifier = Modifier.height(BadgeTileConfig.LinesBetweenPadding))
        }
        ShimmerTileLine(
            width = BadgeTileConfig.LinesMaxWidth,
            height = AppTheme.typography.bodyLarge.lineHeight.value.dp.times(2) + 4.dp,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BadgeTileContainer(
    modifier: Modifier = Modifier,
    contentColor: Color,
    containerColor: Color,
    clickListener: (() -> Unit)?,
    content: @Composable () -> Unit,
) {
    val body: @Composable ColumnScope.() -> Unit = {
        Column(modifier = Modifier.padding(DimenValues.default_padding)) {
            content()
        }
    }
    val elevation = CardDefaults.elevatedCardElevation(
        defaultElevation = 16.dp,
        pressedElevation = 16.dp,
        focusedElevation = 16.dp,
        hoveredElevation = 16.dp,
        draggedElevation = 16.dp,
    )
    if (clickListener != null) {
        Card(
            modifier = modifier,
            shape = AppTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                containerColor = containerColor,
                contentColor = contentColor,
            ),
            elevation = elevation,
            onClick = clickListener,
            content = body,
        )
    } else {
        Card(
            modifier = modifier,
            shape = AppTheme.shapes.medium,
            colors = CardDefaults.cardColors(
                containerColor = containerColor,
                contentColor = contentColor,
            ),
            elevation = elevation,
            content = body,
        )
    }
}

object BadgeTileConfig {
    val LinesMinWidth = 120.dp
    val LinesMaxWidth = 156.dp
    val LinesBetweenPadding = 8.dp
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    PreviewAppTheme {
        BadgeTileState(
            header = TextValue.Simple("Badge tile header text"),
            value = TextValue.Simple("Badge tile text"),
            contentColor = AppTheme.specificColorScheme.onSurface,
            containerColor = AppTheme.specificColorScheme.surface,
            clickListener = {}
        ).Content(Modifier)
    }
}