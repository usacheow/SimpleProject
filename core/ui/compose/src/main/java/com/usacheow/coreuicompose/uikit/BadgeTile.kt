package com.usacheow.coreuicompose.uikit

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.usacheow.coreuicompose.tools.ShimmerState
import com.usacheow.coreuicompose.tools.WidgetState
import com.usacheow.coreuitheme.compose.AppTheme
import com.usacheow.coreuitheme.compose.Dimen
import com.usacheow.corecommon.container.compose.TextValue
import com.usacheow.coreuicompose.tools.SimplePreview
import com.usacheow.coreuicompose.tools.get

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
) : WidgetState {

    @Composable
    override fun Content(modifier: Modifier?) {
        BadgeTile(modifier ?: Modifier, this)
    }

    companion object {
        fun shimmer(needAdaptWidth: Boolean = true) = ShimmerState {
            BadgeTileShimmer(it, needAdaptWidth)
        }
    }
}

@Composable
fun BadgeTile(
    modifier: Modifier = Modifier,
    data: BadgeTileState,
) {
    BadgeCard(
        modifier = modifier,
        needAdaptWidth = data.needAdaptWidth,
        contentColor = data.contentColor,
        backgroundColor = data.color,
        clickListener = data.clickListener,
    ) {
        if (data.header !is TextValue.Empty) {
            Text(
                text = data.header.get(),
                color = AppTheme.commonColors.symbolSecondary,
                style = AppTheme.typography.bodyMedium,
                maxLines = 1,
                modifier = Modifier
                    .padding(bottom = linesBetweenPadding)
                    .widthIn(min = linesMinWidth, max = linesMaxWidth),
            )
        }
        Text(
            text = data.value.get().plus(AnnotatedString("\n")),
            color = AppTheme.commonColors.symbolPrimary,
            style = AppTheme.typography.bodyLarge,
            maxLines = 2,
            modifier = Modifier.widthIn(min = linesMinWidth, max = linesMaxWidth),
        )
    }
}

@Composable
fun BadgeTileShimmer(
    modifier: Modifier = Modifier,
    needAdaptWidth: Boolean = true,
) {
    BadgeCard(
        modifier = modifier,
        needAdaptWidth = needAdaptWidth,
        contentColor = AppTheme.commonColors.symbolPrimary,
        backgroundColor = AppTheme.colorScheme.surface,
        clickListener = null,
    ) {
        ShimmerTileLine(width = linesMinWidth)
        Spacer(modifier = Modifier.height(linesBetweenPadding))
        ShimmerTileLine(width = linesMaxWidth, height = ShimmerTileDefaults.linesHeight.times(2))
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BadgeCard(
    modifier: Modifier = Modifier,
    needAdaptWidth: Boolean,
    contentColor: Color,
    backgroundColor: Color,
    clickListener: (() -> Unit)?,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = when (needAdaptWidth) {
            true -> modifier.wrapContentWidth()
            false -> modifier.fillMaxWidth()
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
    val items = previewBadgeTiles()
    SimplePreview {
        LazyColumn {
            items(items) {
                it.Content()
            }
        }
    }
}

@Composable
fun previewBadgeTiles() = listOf(
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