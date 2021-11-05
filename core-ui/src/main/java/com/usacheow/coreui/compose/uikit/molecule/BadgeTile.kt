package com.usacheow.coreui.compose.uikit.molecule

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.usacheow.coreui.adapter.base.ShimmerState
import com.usacheow.coreui.adapter.base.WidgetState
import com.usacheow.coreui.compose.resources.CommonDimens
import com.usacheow.coreui.compose.resources.Shapes
import com.usacheow.coreui.compose.resources.secondaryTextAlpha
import com.usacheow.coreui.compose.tools.LazySimpleWidgetStatePreview
import com.usacheow.coreui.compose.tools.TextValue
import com.usacheow.coreui.compose.uikit.atom.SpaceTile


private val linesMinWidth = 120.dp
private val linesMaxWidth = 156.dp
private val linesBetweenPadding = 8.dp

data class BadgeTileItem(
    val header: TextValue = TextValue.Empty,
    val value: TextValue,
    val needAdaptWidth: Boolean = true,
    val contentColor: Color,
    val color: Color,
    val clickListener: (() -> Unit)? = null,
) : WidgetState({
    BadgeTile(header, value, needAdaptWidth, contentColor, color, clickListener)
}) {

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
            CompositionLocalProvider(LocalContentAlpha provides secondaryTextAlpha) {
                Text(
                    text = header.get(),
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    modifier = Modifier
                        .padding(bottom = linesBetweenPadding)
                        .widthIn(min = linesMinWidth, max = linesMaxWidth))
            }
        }
        Text(
            text = value.get().plus(AnnotatedString("\n")),
            style = MaterialTheme.typography.bodyLarge,
            maxLines = 2,
            modifier = Modifier.widthIn(
                min = linesMinWidth,
                max = linesMaxWidth))
    }
}

@Composable
fun BadgeTileShimmer(needAdaptWidth: Boolean = true) {
    BadgeCard(
        needAdaptWidth = needAdaptWidth,
        contentColor = LocalContentColor.current,
        backgroundColor = MaterialTheme.colorScheme.surface,
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
        shape = Shapes.medium,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = CommonDimens.elevation_32,
        onClick = clickListener ?: {},
    ) {
        Column(modifier = Modifier.padding(CommonDimens.default_screen_margin)) {
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
internal fun generatePreviewBadgeTiles(): List<WidgetState> = listOf(
    BadgeTileItem.shimmer(),
    BadgeTileItem(
        header = TextValue.Empty,
        value = TextValue.Simple("Badge tile text"),
        needAdaptWidth = true,
        contentColor = MaterialTheme.colorScheme.onSurface,
        color = MaterialTheme.colorScheme.surface,
        clickListener = {}),
    BadgeTileItem(
        header = TextValue.Simple("Badge tile header text"),
        value = TextValue.Simple("Badge tile text"),
        needAdaptWidth = true,
        contentColor = MaterialTheme.colorScheme.onSurface,
        color = MaterialTheme.colorScheme.surface,
        clickListener = {}),
)