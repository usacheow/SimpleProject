package com.usacheow.coreui.compose.uikit.molecule

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.usacheow.coreui.adapter.base.WidgetState
import com.usacheow.coreui.compose.resources.AppTheme
import com.usacheow.coreui.compose.resources.Dimen
import com.usacheow.coreui.compose.tools.ImageValue
import com.usacheow.coreui.compose.tools.LazySimpleWidgetStatePreview
import com.usacheow.coreui.compose.tools.TextValue
import com.usacheow.coreui.compose.tools.doOnClick
import com.usacheow.coreui.compose.uikit.atom.SpaceTile

private val iconPaddingHorizontal = Dimen.default_padding

data class ListTileState(
    val leftImageInfo: ImageValue = ImageValue.Empty,
    val rightImageInfo: ImageValue = ImageValue.Empty,
    val value: TextValue,
    val topDescription: TextValue = TextValue.Empty,
    val bottomDescription: TextValue = TextValue.Empty,
    val clickListener: (() -> Unit)? = null,
) : WidgetState() {

    override val content = @Composable {
        ListTile(leftImageInfo, rightImageInfo, value, topDescription, bottomDescription, clickListener)
    }

    companion object {
        fun shimmer() = ShimmerTileState(
            needTopLine = false,
            needRightIcon = false,
        )
    }
}

@Composable
fun ListTile(
    leftImageInfo: ImageValue = ImageValue.Empty,
    rightImageInfo: ImageValue = ImageValue.Empty,
    value: TextValue,
    topDescription: TextValue = TextValue.Empty,
    bottomDescription: TextValue = TextValue.Empty,
    clickListener: (() -> Unit)? = null,
) {
    val ripplePadding = 8.dp
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(ripplePadding)
            .clip(AppTheme.shapes.medium)
            .doOnClick(onClick = clickListener)
            .padding(Dimen.default_padding - ripplePadding),
    ) {
        Icon(
            icon = leftImageInfo,
            modifier = Modifier.padding(end = iconPaddingHorizontal),
        )
        Column(modifier = Modifier.weight(1f)) {
            if (topDescription !is TextValue.Empty) {
                SecondaryText(topDescription)
                SpaceTile(height = 4.dp)
            }
            PrimaryText(value)
            if (bottomDescription !is TextValue.Empty) {
                SpaceTile(height = 4.dp)
                SecondaryText(bottomDescription)
            }
        }
        Icon(
            icon = rightImageInfo,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .padding(start = iconPaddingHorizontal),
        )
    }
}

@Composable
private fun Icon(icon: ImageValue, modifier: Modifier) {
    icon.get()?.let {
        Icon(
            painter = it,
            contentDescription = "Item icon",
            modifier = modifier.width(36.dp),
        )
    }
}

@Composable
private fun PrimaryText(value: TextValue) {
    Text(
        text = value.get(),
        color = AppTheme.commonColors.symbolPrimary,
        style = AppTheme.typography.bodyLarge,
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
private fun SecondaryText(value: TextValue) {
    Text(
        text = value.get(),
        color = AppTheme.commonColors.symbolSecondary,
        style = AppTheme.typography.bodyMedium,
        modifier = Modifier.fillMaxWidth(),
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ListTilePreview() {
    LazySimpleWidgetStatePreview { generatePreviewListTiles() }
}

@Composable
private fun generatePreviewListTiles(): List<WidgetState> = listOf(
    ListTileState.shimmer(),
    ListTileState(
        leftImageInfo = ImageValue.Empty,
        rightImageInfo = ImageValue.Empty,
        value = TextValue.Simple("Main information"),
        topDescription = TextValue.Empty,
        bottomDescription = TextValue.Empty,
        clickListener = {},
    ),
    ListTileState(
        leftImageInfo = ImageValue.Empty,
        rightImageInfo = ImageValue.Empty,
        value = TextValue.Simple("Main information"),
        topDescription = TextValue.Empty,
        bottomDescription = TextValue.Simple("Bottom description"),
        clickListener = {},
    ),
    ListTileState(
        leftImageInfo = ImageValue.Empty,
        rightImageInfo = ImageValue.Empty,
        value = TextValue.Simple("Main information"),
        topDescription = TextValue.Simple("Top description"),
        bottomDescription = TextValue.Simple("Bottom description"),
        clickListener = {},
    ),
    ListTileState(
        leftImageInfo = ImageValue.Empty,
        rightImageInfo = ImageValue.Vector(Icons.Default.NavigateNext),
        value = TextValue.Simple("Main information"),
        topDescription = TextValue.Empty,
        bottomDescription = TextValue.Empty,
        clickListener = {},
    ),
    ListTileState(
        leftImageInfo = ImageValue.Vector(Icons.Default.AccountCircle),
        rightImageInfo = ImageValue.Vector(Icons.Default.NavigateNext),
        value = TextValue.Simple("Main information"),
        topDescription = TextValue.Empty,
        bottomDescription = TextValue.Simple("Bottom description"),
        clickListener = {},
    ),
    ListTileState(
        leftImageInfo = ImageValue.Vector(Icons.Default.AccountCircle),
        rightImageInfo = ImageValue.Vector(Icons.Default.NavigateNext),
        value = TextValue.Simple("Main information"),
        topDescription = TextValue.Simple("Top description"),
        bottomDescription = TextValue.Simple("Bottom description"),
        clickListener = {},
    ),
)