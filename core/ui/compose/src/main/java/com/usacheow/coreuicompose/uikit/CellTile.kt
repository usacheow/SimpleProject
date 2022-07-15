package com.usacheow.coreuicompose.uikit

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.usacheow.corecommon.container.IconValue
import com.usacheow.corecommon.container.ImageValue
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.TileState
import com.usacheow.coreuicompose.tools.defaultTileRipple
import com.usacheow.coreuicompose.tools.get
import com.usacheow.coreuicompose.uikit.status.ShimmerTile
import com.usacheow.coreuitheme.compose.AppTheme

sealed class CellTileState : TileState {

    data class Data(
        val leftPart: LeftPart? = null,
        val subtitle: TextValue? = null,
        val title: TextValue? = null,
        val value: TextValue? = null,
        val additional: TextValue? = null,
        val rightPart: RightPart? = null,
        val onClick: (() -> Unit)? = null,
    ) : CellTileState()

    object Shimmer : CellTileState()

    @Composable
    override fun Content(modifier: Modifier) {
        CellTile(modifier, this)
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

object CellTileConfig {
    val IconSize = 40.dp
}

@Composable
fun CellTile(
    modifier: Modifier = Modifier,
    data: CellTileState,
) {
    when (data) {
        is CellTileState.Data -> Data(modifier, data)
        is CellTileState.Shimmer -> Shimmer(modifier)
    }
}

@Composable
private fun Data(
    modifier: Modifier = Modifier,
    data: CellTileState.Data,
) {
    Container(
        modifier = modifier,
        onClick = data.onClick,
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
private fun Shimmer(modifier: Modifier = Modifier) {
    Container(
        modifier = modifier,
        onClick = null,
    ) {
        ShimmerTile(
            needBottomLine = false,
            needRightIcon = false,
        )
    }
}

@Composable
private fun Container(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)?,
    content: @Composable RowScope.() -> Unit,
) {
    Row(
        verticalAlignment = Alignment.Top,
        modifier = modifier
            .fillMaxWidth()
            .padding(AppTheme.specificDimens.ripple_outer_padding)
            .defaultTileRipple(onClick = onClick),
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
                style = AppTheme.specificTypography.bodySmall,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        title?.get()?.let {
            Text(
                text = it,
                color = AppTheme.specificColorScheme.symbolPrimary,
                style = AppTheme.specificTypography.bodyLarge,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        value?.get()?.let {
            Text(
                text = it,
                color = AppTheme.specificColorScheme.symbolPrimary,
                style = AppTheme.specificTypography.titleLarge,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        additional?.get()?.let {
            Text(
                text = it,
                color = AppTheme.specificColorScheme.symbolSecondary,
                style = AppTheme.specificTypography.bodyMedium,
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
                .size(CellTileConfig.IconSize)
                .align(Alignment.CenterVertically)
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
                .align(Alignment.CenterVertically),
        )
    }
}