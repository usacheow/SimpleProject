package com.usacheow.coreui.compose.uikit.molecule

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.usacheow.coreui.adapter.base.ShimmerState
import com.usacheow.coreui.adapter.base.WidgetState
import com.usacheow.coreui.compose.resources.AppTheme
import com.usacheow.coreui.compose.resources.Dimen
import com.usacheow.coreui.compose.tools.LazySimpleWidgetStatePreview
import com.usacheow.coreui.compose.tools.TextValue
import com.usacheow.coreui.compose.uikit.atom.SpaceTile

private val linesMinWidth = 120.dp
private val linesMaxWidth = 156.dp
private val linesBetweenPadding = 8.dp

data class BadgeTileState(
    val header: TextValue = TextValue.Empty,
    val value: TextValue,
    val needAdaptWidth: Boolean = true,
    val contentColor: Color,
    val color: Color,
    val clickListener: (() -> Unit)? = null,
) : WidgetState() {

    override val content = @Composable {
        BadgeTile(header, value, needAdaptWidth, contentColor, color, clickListener)
    }

    companion object {
        fun shimmer(needAdaptWidth: Boolean = true) = ShimmerState {
            BadgeTileShimmer(needAdaptWidth = needAdaptWidth)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BadgeTile(
    header: TextValue = TextValue.Empty,
    value: TextValue,
    needAdaptWidth: Boolean = true,
    contentColor: Color,
    color: Color,
    clickListener: (() -> Unit)? = null,
) {
    BadgeCard(
        needAdaptWidth = needAdaptWidth,
        contentColor = contentColor,
        backgroundColor = color,
        clickListener = clickListener,
    ) {
        if (header !is TextValue.Empty) {
            Text(
                text = header.get(),
                color = AppTheme.commonColors.symbolSecondary,
                style = AppTheme.typography.bodyMedium,
                maxLines = 1,
                modifier = Modifier
                    .padding(bottom = linesBetweenPadding)
                    .widthIn(min = linesMinWidth, max = linesMaxWidth),
            )
        }
        Text(
            text = value.get().plus(AnnotatedString("\n")),
            color = AppTheme.commonColors.symbolPrimary,
            style = AppTheme.typography.bodyLarge,
            maxLines = 2,
            modifier = Modifier.widthIn(min = linesMinWidth, max = linesMaxWidth),
        )
    }
}

@Composable
fun BadgeTileShimmer(needAdaptWidth: Boolean = true) {
    BadgeCard(
        needAdaptWidth = needAdaptWidth,
        contentColor = AppTheme.commonColors.symbolPrimary,
        backgroundColor = AppTheme.colorScheme.surface,
        clickListener = null,
    ) {
        ShimmerTileLine(width = linesMinWidth)
        SpaceTile(height = linesBetweenPadding)
        ShimmerTileLine(width = linesMaxWidth, height = ShimmerTileDefaults.linesHeight.times(2))
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BadgeCard(
    needAdaptWidth: Boolean,
    contentColor: Color,
    backgroundColor: Color,
    clickListener: (() -> Unit)?,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = when (needAdaptWidth) {
            true -> Modifier.wrapContentWidth()
            false -> Modifier.fillMaxWidth()
        }.padding(8.dp),
        shape = AppTheme.shapes.medium,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = Dimen.elevation_32,
        onClick = clickListener ?: {},
    ) {
        Column(modifier = Modifier.padding(Dimen.default_padding)) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BadgeTilePreview() {
    LazySimpleWidgetStatePreview { generatePreviewBadgeTiles() }
}

@Composable
private fun generatePreviewBadgeTiles(): List<WidgetState> = listOf(
    BadgeTileState.shimmer(),
    BadgeTileState(
        header = TextValue.Empty,
        value = TextValue.Simple("Badge tile text"),
        needAdaptWidth = true,
        contentColor = AppTheme.colorScheme.onSurface,
        color = AppTheme.colorScheme.surface,
        clickListener = {}
    ),
    BadgeTileState(
        header = TextValue.Simple("Badge tile header text"),
        value = TextValue.Simple("Badge tile text"),
        needAdaptWidth = true,
        contentColor = AppTheme.colorScheme.onSurface,
        color = AppTheme.colorScheme.surface,
        clickListener = {}
    ),
)