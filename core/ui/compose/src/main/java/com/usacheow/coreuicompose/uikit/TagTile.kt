package com.usacheow.coreuicompose.uikit

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.TilesShimmerState
import com.usacheow.coreuicompose.tools.TileState
import com.usacheow.coreuicompose.tools.get
import com.usacheow.coreuicompose.uikit.status.ShimmerTileLine
import com.usacheow.coreuitheme.compose.AppTheme
import com.usacheow.coreuitheme.compose.DimenValues
import com.usacheow.coreuitheme.compose.PreviewAppTheme

data class TagTileState(
    val text: TextValue,
    val isSelected: Boolean = false,
    val unselectedColor: DataColor? = null,
    val selectedColor: DataColor? = null,
    val onClick: () -> Unit,
) : TileState {

    @Composable
    override fun Content(modifier: Modifier) {
        TagTile(modifier, this)
    }

    companion object {
        fun shimmer() = TilesShimmerState { TagTileShimmer(it) }
    }

    data class DataColor(
        val background: Color,
        val content: Color,
    )
}

object TagTileDefaults {

    @Composable
    fun selectedColor() = TagTileState.DataColor(
        background = AppTheme.specificColorScheme.tertiaryContainer,
        content = AppTheme.specificColorScheme.onTertiaryContainer,
    )

    @Composable
    fun unselectedColor() = TagTileState.DataColor(
        background = AppTheme.specificColorScheme.surfaceVariant,
        content = AppTheme.specificColorScheme.onSurfaceVariant,
    )
}

@Composable
fun TagTile(
    modifier: Modifier = Modifier,
    data: TagTileState,
) {
    val color = when {
        data.isSelected -> data.selectedColor ?: TagTileDefaults.selectedColor()
        else -> data.unselectedColor ?: TagTileDefaults.unselectedColor()
    }

    TagTileContainer(modifier = modifier, color = color, onClick = data.onClick) {
        Text(
            text = data.text.get(),
            color = AppTheme.specificColorScheme.symbolPrimary,
            style = AppTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun TagTileShimmer(modifier: Modifier = Modifier) {
    TagTileContainer(modifier = modifier, color = TagTileDefaults.unselectedColor(), onClick = null) {
        ShimmerTileLine(width = 40.dp)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TagTileContainer(
    modifier: Modifier = Modifier,
    color: TagTileState.DataColor,
    onClick: (() -> Unit)?,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = color.background,
            contentColor = color.content,
        ),
        elevation = CardDefaults.cardElevation(),
        shape = AppTheme.shapes.small,
        onClick = onClick ?: {},
    ) {
        Column(
            modifier = Modifier
                .padding(DimenValues.default_padding)
                .align(Alignment.CenterHorizontally),
        ) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    PreviewAppTheme {
        TagTileState(
            text = TextValue.Simple("Tag text"),
            isSelected = false,
            unselectedColor = TagTileDefaults.unselectedColor(),
            selectedColor = TagTileDefaults.selectedColor(),
            onClick = { },
        ).Content(Modifier)
    }
}