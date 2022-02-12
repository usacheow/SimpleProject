package com.usacheow.coreui.compose.uikit.atom

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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

data class DividerState(
    val height: Dp,
    val modifier: Modifier = Modifier,
    var color: Color? = null,
) : WidgetState() {
    override val content = @Composable {
        Divider(
            modifier = modifier,
            thickness = height,
            color = color ?: AppTheme.colorScheme.outline,
        )
    }
}

data class SpacerState(val height: Dp) : WidgetState() {
    override val content = @Composable {
        Spacer(modifier = Modifier.height(height))
    }
}

object DividerDefaults {

    val heightSmall = 1.dp
    val heightMedium = 8.dp
    val heightLarge = 16.dp
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    LazySimpleWidgetStatePreview { generatePreviewDividerTiles() }
}

@Composable
private fun generatePreviewDividerTiles(): List<WidgetState> = listOf(
    SpacerState(height = 32.dp),
    DividerState(
        height = DividerDefaults.heightSmall,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
    ),
    SpacerState(height = 32.dp),
    DividerState(
        height = DividerDefaults.heightMedium,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
    ),
    SpacerState(height = 32.dp),
    DividerState(
        height = DividerDefaults.heightLarge,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
    ),
    SpacerState(height = 32.dp),
)