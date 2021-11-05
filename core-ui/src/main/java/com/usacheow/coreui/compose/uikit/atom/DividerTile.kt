package com.usacheow.coreui.compose.uikit.atom

import android.content.res.Configuration
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.usacheow.coreui.adapter.base.WidgetState
import com.usacheow.coreui.compose.tools.LazySimpleWidgetStatePreview
import com.usacheow.coreui.compose.tools.Margin
import com.usacheow.coreui.compose.tools.margin

data class DividerTileItem(
    val height: Dp,
    val modifier: Modifier = Modifier,
    var color: Color? = null,
) : WidgetState({
    DividerTile(modifier, height, color)
})

data class SpaceTileItem(val height: Dp) : WidgetState({
    SpaceTile(height = height)
})

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
        color = color ?: MaterialTheme.colorScheme.outline)
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun DividerPreview() {
    LazySimpleWidgetStatePreview { generatePreviewDividerTiles() }
}

@Composable
internal fun generatePreviewDividerTiles(): List<WidgetState> = listOf(
    SpaceTileItem(height = 32.dp),
    DividerTileItem(
        height = DividerDefaults.heightSmall,
        modifier = Modifier.margin(Margin.All(start = 16.dp, end = 16.dp))),
    SpaceTileItem(height = 32.dp),
    DividerTileItem(
        height = DividerDefaults.heightMedium,
        modifier = Modifier.margin(Margin.All(start = 16.dp, end = 16.dp))),
    SpaceTileItem(height = 32.dp),
    DividerTileItem(
        height = DividerDefaults.heightLarge,
        modifier = Modifier.margin(Margin.All(start = 16.dp, end = 16.dp))),
    SpaceTileItem(height = 32.dp),
)