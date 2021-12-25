package com.usacheow.coreui.compose.uikit.atom

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.usacheow.coreui.adapter.base.WidgetState
import com.usacheow.coreui.compose.resources.AppTheme
import com.usacheow.coreui.compose.tools.LazySimpleWidgetStatePreview

data class DividerTileState(
    val height: Dp,
    val modifier: Modifier = Modifier,
    var color: Color? = null,
) : WidgetState() {
    override val content = @Composable {
        DividerTile(modifier, height, color)
    }
}

data class SimpleSpaceTileState(val height: Dp) : WidgetState() {
    override val content = @Composable {
        SpaceTile(height = height)
    }
}

object DividerDefaults {

    val heightSmall = 1.dp
    val heightMedium = 8.dp
    val heightLarge = 16.dp
}

@Composable
fun SpaceTile(height: Dp = DividerDefaults.heightSmall) {
    DividerTile(height = height, color = Color.Transparent)
}

@Composable
fun DividerTile(
    modifier: Modifier = Modifier,
    height: Dp = DividerDefaults.heightSmall,
    color: Color? = null,
) {
    Divider(
        modifier = modifier,
        thickness = height,
        color = color ?: AppTheme.colorScheme.outline,
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    LazySimpleWidgetStatePreview { generatePreviewDividerTiles() }
}

@Composable
private fun generatePreviewDividerTiles(): List<WidgetState> = listOf(
    SimpleSpaceTileState(height = 32.dp),
    DividerTileState(
        height = DividerDefaults.heightSmall,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
    ),
    SimpleSpaceTileState(height = 32.dp),
    DividerTileState(
        height = DividerDefaults.heightMedium,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
    ),
    SimpleSpaceTileState(height = 32.dp),
    DividerTileState(
        height = DividerDefaults.heightLarge,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
    ),
    SimpleSpaceTileState(height = 32.dp),
)