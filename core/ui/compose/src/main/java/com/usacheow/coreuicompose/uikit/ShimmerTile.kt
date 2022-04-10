package com.usacheow.coreuicompose.uikit

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.usacheow.coreuicompose.tools.SimplePreview
import com.usacheow.coreuicompose.tools.defaultPlaceholder
import com.usacheow.coreuitheme.compose.AppTheme
import com.usacheow.coreuitheme.compose.Dimen

@Composable
fun ShimmerTile(
    modifier: Modifier = Modifier,
    needLeftIcon: Boolean = true,
    needRightIcon: Boolean = true,
    needTopLine: Boolean = true,
    needMiddleLine: Boolean = true,
    needBottomLine: Boolean = true,
) {
    Row(
        modifier = modifier
            .background(AppTheme.colorScheme.background)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Dimen.default_padding),
    ) {
        if (needLeftIcon) ShimmerTileCircle()
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            if (needTopLine) ShimmerTileLine(width = 100.dp)
            if (needMiddleLine) ShimmerTileLine(width = 200.dp)
            if (needBottomLine) ShimmerTileLine(width = 150.dp)
        }
        if (needRightIcon) ShimmerTileCircle()
    }
}

@Composable
fun ShimmerTileLine(
    width: Dp,
    modifier: Modifier = Modifier,
    height: Dp = ShimmerTileConfig.linesHeight,
) {
    Surface(
        color = AppTheme.commonColors.shimmer,
        shape = CircleShape,
        content = {},
        modifier = modifier
            .height(height)
            .width(width)
            .defaultPlaceholder(),
    )
}

@Composable
fun ShimmerTileCircle(
    modifier: Modifier = Modifier,
    size: Dp = 36.dp,
) {
    Surface(
        color = AppTheme.commonColors.shimmer,
        shape = CircleShape,
        content = {},
        modifier = modifier
            .size(size)
            .defaultPlaceholder(),
    )
}

private object ShimmerTileConfig {

    val linesHeight = 16.dp
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    SimplePreview {
        ShimmerTile(
            modifier = Modifier.padding(8.dp),
            needLeftIcon = true,
            needRightIcon = true,
            needTopLine = true,
            needMiddleLine = true,
            needBottomLine = true,
        )
    }
}