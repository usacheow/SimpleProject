package com.usacheow.coreui.compose.uikit.molecule

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.usacheow.coreui.adapter.base.WidgetState
import com.usacheow.coreui.compose.resources.CommonDimens
import com.usacheow.coreui.compose.resources.LocalCommonColors
import com.usacheow.coreui.compose.tools.LazySimpleWidgetStatePreview
import com.usacheow.coreui.compose.uikit.atom.SpaceTile

data class ShimmerTileItem(
    val needLeftIcon: Boolean = true,
    val needRightIcon: Boolean = true,
    val needTopLine: Boolean = true,
    val needMiddleLine: Boolean = true,
    val needBottomLine: Boolean = true,
) : WidgetState() {
    override val content = @Composable {
        ShimmerTile(
            needLeftIcon = needLeftIcon,
            needRightIcon = needRightIcon,
            needTopLine = needTopLine,
            needMiddleLine = needMiddleLine,
            needBottomLine = needBottomLine)
    }
}

object ShimmerTileDefaults {

    val linesHeight = 16.dp
}

@Composable
fun ShimmerTile(
    needLeftIcon: Boolean = true,
    needRightIcon: Boolean = true,
    needTopLine: Boolean = true,
    needMiddleLine: Boolean = true,
    needBottomLine: Boolean = true,
) {
    val startLinesPadding = when {
        needLeftIcon -> CommonDimens.default_screen_margin
        else -> 0.dp
    }
    val endLinesPadding = when {
        needRightIcon -> CommonDimens.default_screen_margin
        else -> 0.dp
    }

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(CommonDimens.default_screen_margin)) {
        if (needLeftIcon) {
            ShimmerTileCircle()
        }
        Column(modifier = Modifier
            .weight(1f)
            .padding(start = startLinesPadding, end = endLinesPadding)) {
            if (needTopLine) {
                ShimmerTileLine(width = 100.dp)
                SpaceTile(height = 4.dp)
            }
            if (needMiddleLine) {
                ShimmerTileLine(width = 200.dp)
            }
            if (needBottomLine) {
                SpaceTile(height = 4.dp)
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
        color = LocalCommonColors.current.shimmer,
        shape = RoundedCornerShape(percent = 50),
        content = {},
        modifier = modifier
            .height(height)
            .width(width))
}

@Composable
fun ShimmerTileCircle(
    modifier: Modifier = Modifier,
    size: Dp = 36.dp,
) {
    Surface(
        color = LocalCommonColors.current.shimmer,
        modifier = modifier.size(size),
        shape = RoundedCornerShape(percent = 50),
        content = {})
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ShimmerTilePreview() {
    LazySimpleWidgetStatePreview { generatePreviewShimmerTile() }
}

@Composable
internal fun generatePreviewShimmerTile(): List<WidgetState> = listOf(
    ShimmerTileItem(
        needLeftIcon = false,
        needRightIcon = false,
        needTopLine = false,
        needMiddleLine = true,
        needBottomLine = false),
    ShimmerTileItem(
        needLeftIcon = false,
        needRightIcon = false,
        needTopLine = false,
        needMiddleLine = true,
        needBottomLine = true),
    ShimmerTileItem(
        needLeftIcon = true,
        needRightIcon = false,
        needTopLine = true,
        needMiddleLine = true,
        needBottomLine = false),
    ShimmerTileItem(
        needLeftIcon = true,
        needRightIcon = true,
        needTopLine = true,
        needMiddleLine = true,
        needBottomLine = false),
    ShimmerTileItem(
        needLeftIcon = true,
        needRightIcon = true,
        needTopLine = true,
        needMiddleLine = true,
        needBottomLine = true),
)