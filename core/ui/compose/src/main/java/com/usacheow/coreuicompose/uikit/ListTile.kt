package com.usacheow.coreuicompose.uikit

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.usacheow.coreuicompose.tools.WidgetState
import com.usacheow.coreuitheme.compose.AppTheme
import com.usacheow.coreuitheme.compose.Dimen
import com.usacheow.corecommon.container.compose.ImageValue
import com.usacheow.corecommon.container.compose.TextValue
import com.usacheow.coreuicompose.tools.SimplePreview
import com.usacheow.coreuicompose.tools.doOnClick
import com.usacheow.coreuicompose.tools.get

private val iconPaddingHorizontal = Dimen.default_padding

data class ListTileState(
    val leftImageInfo: ImageValue = ImageValue.Empty,
    val rightImageInfo: ImageValue = ImageValue.Empty,
    val value: TextValue,
    val topDescription: TextValue = TextValue.Empty,
    val bottomDescription: TextValue = TextValue.Empty,
    val clickListener: (() -> Unit)? = null,
) : WidgetState {

    @Composable
    override fun Content(modifier: Modifier) {
        ListTile(modifier, this)
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
    modifier: Modifier = Modifier,
    data: ListTileState,
) {
    val ripplePadding = 8.dp
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(ripplePadding)
            .clip(AppTheme.shapes.medium)
            .doOnClick(onClick = data.clickListener)
            .padding(Dimen.default_padding - ripplePadding),
    ) {
        Icon(
            icon = data.leftImageInfo,
            modifier = Modifier.padding(end = iconPaddingHorizontal),
        )
        Column(modifier = Modifier.weight(1f)) {
            if (data.topDescription !is TextValue.Empty) {
                SecondaryText(data.topDescription)
                Spacer(modifier = Modifier.height(4.dp))
            }
            PrimaryText(data.value)
            if (data.bottomDescription !is TextValue.Empty) {
                Spacer(modifier = Modifier.height(4.dp))
                SecondaryText(data.bottomDescription)
            }
        }
        Icon(
            icon = data.rightImageInfo,
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
            tint = AppTheme.commonColors.symbolPrimary,
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
    SimplePreview {
        LazyColumn {
            items(previewListTiles()) {
                it.Content()
            }
        }
    }
}

private fun previewListTiles(): List<WidgetState> = listOf(
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