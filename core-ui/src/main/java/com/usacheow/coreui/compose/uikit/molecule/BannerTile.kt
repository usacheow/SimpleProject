package com.usacheow.coreui.compose.uikit.molecule

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicVideo
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.usacheow.coreui.adapter.base.ShimmerState
import com.usacheow.coreui.adapter.base.WidgetState
import com.usacheow.coreui.compose.resources.CommonDimens
import com.usacheow.coreui.compose.resources.LocalCommonColors
import com.usacheow.coreui.compose.resources.Shapes
import com.usacheow.coreui.compose.tools.ImageValue
import com.usacheow.coreui.compose.tools.LazySimpleWidgetStatePreview
import com.usacheow.coreui.compose.tools.TextValue
import com.usacheow.coreui.compose.uikit.atom.SpaceTile

private val iconSize = 36.dp
private val lineMaxWidth = 144.dp
private val linePaddingEnd = 48.dp
private val linePaddingBottom = 4.dp

data class BannerTileItem(
    val icon: ImageValue,
    val text: TextValue,
    val clickListener: (() -> Unit)? = null,
) : WidgetState({
    BannerTile(icon, text, clickListener)
}) {

    companion object {
        fun shimmer() = ShimmerState { BannerTileShimmer() }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BannerTile(
    icon: ImageValue,
    text: TextValue,
    clickListener: (() -> Unit)? = null,
) {
    BannerCard(clickListener = clickListener) {
        Text(
            text = text.get().plus(AnnotatedString("\n")),
            style = MaterialTheme.typography.headlineSmall,
            maxLines = 2,
            modifier = Modifier
                .widthIn(max = lineMaxWidth)
                .padding(bottom = linePaddingBottom, end = linePaddingEnd))
        icon.get()?.let {
            Icon(
                painter = it,
                contentDescription = "Banner icon",
                modifier = Modifier
                    .width(iconSize)
                    .align(Alignment.End))
        }
    }
}

@Composable
fun BannerTileShimmer() {
    BannerCard(clickListener = null) {
        ShimmerTileLine(
            width = lineMaxWidth,
            modifier = Modifier.padding(end = linePaddingEnd))
        SpaceTile(height = linePaddingBottom)
        ShimmerTileCircle(
            size = iconSize,
            modifier = Modifier.align(Alignment.End))
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun BannerCard(
    clickListener: (() -> Unit)?,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        backgroundColor = LocalCommonColors.current.surfaceVariant,
        contentColor = LocalCommonColors.current.onSurfaceVariant,
        elevation = CommonDimens.elevation_0,
        shape = Shapes.medium,
        onClick = clickListener ?: {},
        modifier = Modifier.wrapContentWidth().padding(8.dp),
    ) {
        Column(modifier = Modifier.padding(CommonDimens.default_screen_margin).fillMaxWidth()) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BannerTilePreview() {
    LazySimpleWidgetStatePreview { generatePreviewBannerTiles() }
}

@Composable
internal fun generatePreviewBannerTiles(): List<WidgetState> = listOf(
    BannerTileItem.shimmer(),
    BannerTileItem(
        icon = ImageValue.Vector(Icons.Default.MusicVideo),
        text = TextValue.Simple("Banner tile text"),
        clickListener = {}),
)