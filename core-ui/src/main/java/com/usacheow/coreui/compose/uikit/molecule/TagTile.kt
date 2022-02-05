package com.usacheow.coreui.compose.uikit.molecule

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.usacheow.coreui.adapter.base.ShimmerState
import com.usacheow.coreui.adapter.base.WidgetState
import com.usacheow.coreui.compose.resources.AppTheme
import com.usacheow.coreui.compose.resources.Dimen
import com.usacheow.coreui.compose.tools.LazySimpleWidgetStatePreview
import com.usacheow.core.resource.compose.TextValue
import com.usacheow.coreui.compose.tools.get

data class TagTileState(
    val text: TextValue,
    val isSelected: Boolean = false,
    val unselectedColor: DataColor? = null,
    val selectedColor: DataColor? = null,
    val clickListener: () -> Unit,
) : WidgetState() {

    override val content = @Composable {
        TagTile(
            text = text,
            isSelected = isSelected,
            unselectedColor = unselectedColor,
            selectedColor = selectedColor,
            clickListener = clickListener,
        )
    }

    companion object {
        fun shimmer() = ShimmerState { TagTileShimmer() }
    }

    data class DataColor(
        val background: Color,
        val content: Color,
    )
}

object TagTileDefaults {

    @Composable
    fun selectedColor() = TagTileState.DataColor(
        background = AppTheme.commonColors.surfaceVariant,
        content = AppTheme.commonColors.onSurfaceVariant,
    )

    @Composable
    fun unselectedColor() = TagTileState.DataColor(
        background = AppTheme.commonColors.surface,
        content = AppTheme.commonColors.onSurface,
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TagTile(
    text: TextValue,
    isSelected: Boolean = false,
    unselectedColor: TagTileState.DataColor? = null,
    selectedColor: TagTileState.DataColor? = null,
    clickListener: () -> Unit,
) {
    val color = when {
        isSelected -> selectedColor ?: TagTileDefaults.selectedColor()
        else -> unselectedColor ?: TagTileDefaults.unselectedColor()
    }

    TagCard(color = color, clickListener = clickListener) {
        Text(
            text = text.get(),
            color = AppTheme.commonColors.symbolPrimary,
            style = AppTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
fun TagTileShimmer() {
    TagCard(color = TagTileDefaults.unselectedColor(), clickListener = null) {
        ShimmerTileLine(width = 40.dp)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun TagCard(
    color: TagTileState.DataColor,
    clickListener: (() -> Unit)?,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = Modifier
            .wrapContentWidth()
            .padding(8.dp),
        backgroundColor = color.background,
        contentColor = color.content,
        elevation = Dimen.elevation_0,
        shape = AppTheme.shapes.small,
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
private fun TagTilePreview() {
    val selectedTagIndex = remember { mutableStateOf(0) }
    LazySimpleWidgetStatePreview { generatePreviewTagTiles(selectedTagIndex) }
}

@Composable
private fun generatePreviewTagTiles(selectedTagIndex: MutableState<Int>): List<WidgetState> = listOf(
    TagTileState.shimmer(),
    TagTileState(
        text = TextValue.Simple("Tag text"),
        isSelected = selectedTagIndex.value == 0,
        unselectedColor = TagTileDefaults.unselectedColor(),
        selectedColor = TagTileDefaults.selectedColor(),
        clickListener = { selectedTagIndex.value = 0 },
    ),
    TagTileState(
        text = TextValue.Simple("Tag text"),
        isSelected = selectedTagIndex.value == 1,
        unselectedColor = TagTileDefaults.unselectedColor(),
        selectedColor = TagTileDefaults.selectedColor(),
        clickListener = { selectedTagIndex.value = 1 },
    ),
)