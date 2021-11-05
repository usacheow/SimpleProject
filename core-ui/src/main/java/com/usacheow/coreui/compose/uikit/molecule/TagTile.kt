package com.usacheow.coreui.compose.uikit.molecule

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.MaterialTheme
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
import com.usacheow.coreui.compose.resources.CommonDimens
import com.usacheow.coreui.compose.resources.LocalCommonColors
import com.usacheow.coreui.compose.resources.Shapes
import com.usacheow.coreui.compose.tools.LazySimpleWidgetStatePreview
import com.usacheow.coreui.compose.tools.TextValue

data class TagTileItem(
    val text: TextValue,
    val isSelected: Boolean = false,
    val unselectedColor: DataColor? = null,
    val selectedColor: DataColor? = null,
    val clickListener: () -> Unit,
) : WidgetState({
    TagTile(
        text = text,
        isSelected = isSelected,
        unselectedColor = unselectedColor,
        selectedColor = selectedColor,
        clickListener = clickListener,
    )
}) {

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
    fun selectedColor() = TagTileItem.DataColor(
        background = LocalCommonColors.current.surfaceVariant,
        content = LocalCommonColors.current.onSurfaceVariant,
    )

    @Composable
    fun unselectedColor() = TagTileItem.DataColor(
        background = LocalCommonColors.current.surface,
        content = LocalCommonColors.current.onSurface,
    )
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TagTile(
    text: TextValue,
    isSelected: Boolean = false,
    unselectedColor: TagTileItem.DataColor? = null,
    selectedColor: TagTileItem.DataColor? = null,
    clickListener: () -> Unit,
) {
    val color = when {
        isSelected -> selectedColor ?: TagTileDefaults.selectedColor()
        else -> unselectedColor ?: TagTileDefaults.unselectedColor()
    }

    TagCard(color = color, clickListener = clickListener) {
        Text(
            text = text.get(),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center)
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
    color: TagTileItem.DataColor,
    clickListener: (() -> Unit)?,
    content: @Composable () -> Unit,
) {
    Card(
        modifier = Modifier.wrapContentWidth().padding(8.dp),
        backgroundColor = color.background,
        contentColor = color.content,
        elevation = CommonDimens.elevation_0,
        shape = Shapes.small,
        onClick = clickListener ?: {},
    ) {
        Column(modifier = Modifier.padding(CommonDimens.default_screen_margin)) {
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
internal fun generatePreviewTagTiles(selectedTagIndex: MutableState<Int>): List<WidgetState> = listOf(
    TagTileItem.shimmer(),
    TagTileItem(
        text = TextValue.Simple("Tag text"),
        isSelected = selectedTagIndex.value == 0,
        unselectedColor = TagTileDefaults.unselectedColor(),
        selectedColor = TagTileDefaults.selectedColor(),
        clickListener = { selectedTagIndex.value = 0 }),
    TagTileItem(
        text = TextValue.Simple("Tag text"),
        isSelected = selectedTagIndex.value == 1,
        unselectedColor = TagTileDefaults.unselectedColor(),
        selectedColor = TagTileDefaults.selectedColor(),
        clickListener = { selectedTagIndex.value = 1 }),
)