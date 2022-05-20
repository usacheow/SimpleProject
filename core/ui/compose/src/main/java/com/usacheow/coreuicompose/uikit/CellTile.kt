package com.usacheow.coreuicompose.uikit

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.usacheow.corecommon.container.IconValue
import com.usacheow.corecommon.container.ImageValue
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.ShimmerState
import com.usacheow.coreuicompose.tools.WidgetState
import com.usacheow.coreuicompose.tools.defaultTileRipple
import com.usacheow.coreuicompose.tools.get
import com.usacheow.coreuicompose.uikit.status.ShimmerTile
import com.usacheow.coreuitheme.compose.AppTheme
import com.usacheow.coreuitheme.compose.DimenValues
import com.usacheow.coreuitheme.compose.PreviewAppTheme

data class CellTileState(
    val leftPart: LeftPart? = null,
    val subtitle: TextValue? = null,
    val title: TextValue? = null,
    val value: TextValue? = null,
    val additional: TextValue? = null,
    val rightPart: RightPart? = null,
    val clickListener: (() -> Unit)? = null,
) : WidgetState {

    @Composable
    override fun Content(modifier: Modifier) {
        CellTile(modifier, this)
    }

    companion object {
        fun shimmer() = ShimmerState {
            CellTileContainer(
                modifier = it,
                clickListener = null,
            ) {
                ShimmerTile(
                    needBottomLine = false,
                    needRightIcon = false,
                )
            }
        }
    }

    sealed class LeftPart {

        data class Icon(
            val icon: IconValue,
            val background: IconValue? = null,
        ) : LeftPart()

        data class Logo(
            val source: ImageValue,
        ) : LeftPart()
    }

    sealed class RightPart {

        data class ActionIcon(val source: ImageValue) : RightPart()
        data class Logo(val source: ImageValue) : RightPart()
        data class Switch(val isChecked: Boolean) : RightPart()
    }
}

@Composable
fun CellTile(
    modifier: Modifier = Modifier,
    data: CellTileState,
) {
    CellTileContainer(
        modifier = modifier,
        clickListener = data.clickListener,
    ) {
        LeftPart(data.leftPart)
        MiddlePart(
            subtitle = data.subtitle,
            title = data.title,
            value = data.value,
            additional = data.additional,
        )
        RightPart(data.rightPart)
    }
}

@Composable
private fun CellTileContainer(
    modifier: Modifier = Modifier,
    clickListener: (() -> Unit)?,
    content: @Composable RowScope.() -> Unit,
) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = modifier
            .fillMaxWidth()
            .padding(DimenValues.ripple_outer_padding)
            .defaultTileRipple(onClick = clickListener),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        content = content,
    )
}

@Composable
private fun LeftPart(data: CellTileState.LeftPart?) {
    data ?: return
    when (data) {
        is CellTileState.LeftPart.Icon -> Box {
            data.background?.get()?.let {
                Image(
                    painter = it,
                    contentDescription = null,
                    modifier = Modifier.size(CellTileConfig.IconSize),
                )
            }
            Icon(
                painter = data.icon.get(),
                tint = AppTheme.specificColorScheme.symbolPrimary,
                contentDescription = null,
                modifier = Modifier
                    .size(CellTileConfig.IconSize)
                    .padding(8.dp),
            )
        }
        is CellTileState.LeftPart.Logo -> Image(
            painter = data.source.get(),
            contentDescription = null,
            modifier = Modifier.size(CellTileConfig.IconSize),
        )
    }
}

@Composable
private fun RowScope.MiddlePart(
    subtitle: TextValue? = null,
    title: TextValue? = null,
    value: TextValue? = null,
    additional: TextValue? = null,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = Modifier.weight(1f),
    ) {
        subtitle?.get()?.let {
            Text(
                text = it,
                color = AppTheme.specificColorScheme.symbolSecondary,
                style = AppTheme.typography.bodySmall,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        title?.get()?.let {
            Text(
                text = it,
                color = AppTheme.specificColorScheme.symbolPrimary,
                style = AppTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        value?.get()?.let {
            Text(
                text = it,
                color = AppTheme.specificColorScheme.symbolPrimary,
                style = AppTheme.typography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        additional?.get()?.let {
            Text(
                text = it,
                color = AppTheme.specificColorScheme.symbolSecondary,
                style = AppTheme.typography.bodyMedium,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
private fun RowScope.RightPart(data: CellTileState.RightPart?) {
    data ?: return
    when (data) {
        is CellTileState.RightPart.ActionIcon -> Icon(
            painter = data.source.get(),
            tint = AppTheme.specificColorScheme.symbolPrimary,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .size(CellTileConfig.IconSize)
                .padding(8.dp),
        )
        is CellTileState.RightPart.Logo -> Image(
            painter = data.source.get(),
            contentDescription = null,
            modifier = Modifier
                .size(CellTileConfig.IconSize)
                .align(Alignment.CenterVertically),
        )
        is CellTileState.RightPart.Switch -> Switch(
            checked = data.isChecked,
            onCheckedChange = null,
            modifier = Modifier
                .height(24.dp)
                .align(Alignment.CenterVertically),
        )
    }
}

object CellTileConfig {
    val IconSize = 40.dp
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    PreviewAppTheme {
        CellTileState(
            subtitle = TextValue.Simple("Subtitle"),
            title = TextValue.Simple("Title"),
            value = TextValue.Simple("Value"),
            additional = TextValue.Simple("Additional"),
            rightPart = CellTileState.RightPart.Switch(true),
            clickListener = {},
        ).Content(Modifier)
    }
}