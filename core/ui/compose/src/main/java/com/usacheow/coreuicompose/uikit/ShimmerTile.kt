package com.usacheow.coreuicompose.uikit

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.usacheow.coreuicompose.tools.WidgetState
import com.usacheow.coreuitheme.compose.AppTheme
import com.usacheow.coreuitheme.compose.CircleShape
import com.usacheow.coreuitheme.compose.Dimen
import com.usacheow.coreuicompose.tools.SimplePreview

data class ShimmerTileState(
    val needLeftIcon: Boolean = true,
    val needRightIcon: Boolean = true,
    val needTopLine: Boolean = true,
    val needMiddleLine: Boolean = true,
    val needBottomLine: Boolean = true,
) : WidgetState {

    @Composable
    override fun Content(modifier: Modifier) {
        ShimmerTile(
            modifier = modifier,
            needLeftIcon = needLeftIcon,
            needRightIcon = needRightIcon,
            needTopLine = needTopLine,
            needMiddleLine = needMiddleLine,
            needBottomLine = needBottomLine,
        )
    }
}

object ShimmerTileDefaults {

    val linesHeight = 16.dp
}

@Composable
fun ShimmerTile(
    modifier: Modifier = Modifier,
    needLeftIcon: Boolean = true,
    needRightIcon: Boolean = true,
    needTopLine: Boolean = true,
    needMiddleLine: Boolean = true,
    needBottomLine: Boolean = true,
) {
    val startLinesPadding = when {
        needLeftIcon -> Dimen.default_padding
        else -> 0.dp
    }
    val endLinesPadding = when {
        needRightIcon -> Dimen.default_padding
        else -> 0.dp
    }

    Row(
        modifier = modifier
            .background(AppTheme.colorScheme.background)
            .fillMaxWidth()
            .padding(Dimen.default_padding),
    ) {
        if (needLeftIcon) {
            ShimmerTileCircle()
        }
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = startLinesPadding, end = endLinesPadding),
        ) {
            if (needTopLine) {
                ShimmerTileLine(width = 100.dp)
                Spacer(modifier = Modifier.height(4.dp))
            }
            if (needMiddleLine) {
                ShimmerTileLine(width = 200.dp)
            }
            if (needBottomLine) {
                Spacer(modifier = Modifier.height(4.dp))
                ShimmerTileLine(width = 150.dp)
            }
        }
        if (needRightIcon) {
            ShimmerTileCircle()
        }
    }
}

@Composable
fun ShimmerTileLine(
    width: Dp,
    modifier: Modifier = Modifier,
    height: Dp = ShimmerTileDefaults.linesHeight,
) {
    Surface(
        color = AppTheme.commonColors.shimmer,
        shape = CircleShape,
        content = {},
        modifier = modifier
            .height(height)
            .width(width),
    )
}

@Composable
fun ShimmerTileCircle(
    modifier: Modifier = Modifier,
    size: Dp = 36.dp,
) {
    Surface(
        color = AppTheme.commonColors.shimmer,
        modifier = modifier.size(size),
        shape = CircleShape,
        content = {},
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ShimmerTilePreview() {
    SimplePreview {
        LazyColumn {
            items(previewShimmerTiles()) {
                it.Content()
            }
        }
    }
}

private fun previewShimmerTiles() = listOf(
    ShimmerTileState(
        needLeftIcon = false,
        needRightIcon = false,
        needTopLine = false,
        needMiddleLine = true,
        needBottomLine = false,
    ),
    ShimmerTileState(
        needLeftIcon = false,
        needRightIcon = false,
        needTopLine = false,
        needMiddleLine = true,
        needBottomLine = true,
    ),
    ShimmerTileState(
        needLeftIcon = true,
        needRightIcon = false,
        needTopLine = true,
        needMiddleLine = true,
        needBottomLine = false,
    ),
    ShimmerTileState(
        needLeftIcon = true,
        needRightIcon = true,
        needTopLine = true,
        needMiddleLine = true,
        needBottomLine = false,
    ),
    ShimmerTileState(
        needLeftIcon = true,
        needRightIcon = true,
        needTopLine = true,
        needMiddleLine = true,
        needBottomLine = true,
    ),
)