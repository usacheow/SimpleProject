package com.usacheow.coreui.compose.uikit.molecule

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.usacheow.coreui.adapter.base.WidgetState
import com.usacheow.coreui.compose.resources.CommonDimens
import com.usacheow.coreui.compose.resources.AppTheme
import com.usacheow.coreui.compose.resources.CircleShape
import com.usacheow.coreui.compose.tools.LazySimpleWidgetStatePreview

data class ProgressTileState(
    val maxValue: Float,
    val currentValue: Float,
    val color: Color,
) : WidgetState() {

    override val content = @Composable {
        ProgressTile(maxValue, currentValue, color)
    }

    companion object {
        fun shimmer() = ShimmerTileState(
            needTopLine = false,
            needRightIcon = false,
            needBottomLine = false,
            needLeftIcon = false)
    }
}

@Composable
fun ProgressTile(
    maxValue: Float,
    currentValue: Float,
    color: Color,
) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(CommonDimens.default_padding)) {
        if (currentValue != 0f) {
            Line(color, currentValue)
        }
        val nonActivePercent = maxValue - currentValue
        if (nonActivePercent != 0f) {
            Line(AppTheme.commonColors.transparent, nonActivePercent)
        }
    }
}

@Composable
private fun RowScope.Line(color: Color, weight: Float) {
    Surface(
        shape = CircleShape,
        color = color,
        content = {},
        modifier = Modifier
            .weight(weight)
            .height(16.dp))
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ProgressTilePreview() {
    LazySimpleWidgetStatePreview { generatePreviewProgressTiles() }
}

@Composable
internal fun generatePreviewProgressTiles(): List<WidgetState> = listOf(
    ProgressTileState.shimmer(),
    ProgressTileState(
        maxValue = 100f,
        currentValue = 30f,
        color = AppTheme.commonColors.primary),
    ProgressTileState(
        maxValue = 100f,
        currentValue = 70f,
        color = AppTheme.commonColors.primary),
    ProgressTileState(
        maxValue = 100f,
        currentValue = 100f,
        color = AppTheme.commonColors.primary),
)