package com.usacheow.coreuicompose.uikit

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.usacheow.corecommon.container.ImageValue
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.ShimmerState
import com.usacheow.coreuicompose.tools.SimplePreview
import com.usacheow.coreuicompose.tools.WidgetState
import com.usacheow.coreuicompose.tools.doOnClick
import com.usacheow.coreuicompose.tools.get
import com.usacheow.coreuitheme.compose.AppTheme
import com.usacheow.coreuitheme.compose.DimenValues

data class ListTileState(
    val leftImageInfo: ImageValue? = null,
    val rightImageInfo: ImageValue? = null,
    val value: TextValue,
    val topDescription: TextValue? = null,
    val bottomDescription: TextValue? = null,
    val clickListener: (() -> Unit)? = null,
) : WidgetState {

    @Composable
    override fun Content(modifier: Modifier) {
        ListTile(modifier, this)
    }

    companion object {
        fun shimmer() = ShimmerState {
            ShimmerTile(
                modifier = it.padding(ListTileConfig.RipplePadding + ListTileConfig.ContentPadding),
                needTopLine = false,
                needRightIcon = false,
            )
        }
    }
}

@Composable
fun ListTile(
    modifier: Modifier = Modifier,
    data: ListTileState,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(ListTileConfig.RipplePadding)
            .clip(AppTheme.shapes.small)
            .doOnClick(onClick = data.clickListener)
            .padding(ListTileConfig.ContentPadding),
        horizontalArrangement = Arrangement.spacedBy(ListTileConfig.IconPaddingHorizontal),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(icon = data.leftImageInfo)
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            SecondaryText(data.topDescription)
            PrimaryText(data.value)
            SecondaryText(data.bottomDescription)
        }
        Icon(icon = data.rightImageInfo)
    }
}

@Composable
private fun RowScope.Icon(icon: ImageValue?) {
    icon?.get()?.let {
        Icon(
            painter = it,
            tint = AppTheme.specificColorScheme.symbolPrimary,
            contentDescription = "Item icon",
            modifier = Modifier.align(Alignment.CenterVertically),
        )
    }
}

@Composable
private fun PrimaryText(value: TextValue) {
    Text(
        text = value.get(),
        color = AppTheme.specificColorScheme.symbolPrimary,
        style = AppTheme.typography.bodyLarge,
        modifier = Modifier.fillMaxWidth(),
    )
}

@Composable
private fun SecondaryText(value: TextValue?) {
    value?.get()?.let {
        Text(
            text = it,
            color = AppTheme.specificColorScheme.symbolSecondary,
            style = AppTheme.typography.bodyMedium,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

private object ListTileConfig {
    val IconPaddingHorizontal = DimenValues.default_padding
    val RipplePadding = 8.dp
    val ContentPadding = DimenValues.default_padding - RipplePadding
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    SimplePreview {
        ListTileState(
            leftImageInfo = AppTheme.specificIcons.account.toImageValue(),
            rightImageInfo = AppTheme.specificIcons.navigateNext.toImageValue(),
            value = TextValue.Simple("Main information"),
            topDescription = TextValue.Simple("Top description"),
            bottomDescription = TextValue.Simple("Bottom description"),
            clickListener = {},
        ).Content(Modifier)
    }
}