package com.usacheow.coreuicompose.uikit

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.usacheow.corecommon.container.IconValue
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.TileState
import com.usacheow.coreuicompose.tools.defaultTileRipple
import com.usacheow.coreuicompose.tools.get
import com.usacheow.coreuicompose.uikit.status.ShimmerTile
import com.usacheow.coreuitheme.compose.AppTheme

sealed class HeaderTileState : TileState {

    data class Data(
        val value: TextValue,
        val action: Action? = null,
        val onClick: (() -> Unit)? = null,
        val type: Type = Type.MediumPrimary,
    ) : HeaderTileState()

    data class Shimmer(
        val type: Type = Type.MediumPrimary,
    ) : HeaderTileState()

    @Composable
    override fun Content(modifier: Modifier) {
        HeaderTile(modifier, this)
    }

    companion object {
        fun largePrimary(value: TextValue) = Data(value = value, type = Type.LargePrimary)
        fun largeSecondary(value: TextValue) = Data(value = value, type = Type.LargeSecondary)
        fun mediumPrimary(value: TextValue) = Data(value = value, type = Type.MediumPrimary)
        fun mediumSecondary(value: TextValue) = Data(value = value, type = Type.MediumSecondary)
        fun smallPrimary(value: TextValue) = Data(value = value, type = Type.SmallPrimary)
        fun smallSecondary(value: TextValue) = Data(value = value, type = Type.SmallSecondary)
    }

    enum class Type {
        LargePrimary, MediumPrimary, SmallPrimary,
        LargeSecondary, MediumSecondary, SmallSecondary,
    }

    sealed class Action {

        data class Text(
            val text: TextValue,
        ) : Action()

        data class Icon(
            val icon: IconValue,
        ) : Action()
    }
}

@Composable
fun HeaderTile(
    modifier: Modifier = Modifier,
    data: HeaderTileState,
) {
    when (data) {
        is HeaderTileState.Data -> Data(modifier, data)
        is HeaderTileState.Shimmer -> Shimmer(modifier, data)
    }
}

@Composable
private fun Data(
    modifier: Modifier = Modifier,
    data: HeaderTileState.Data,
) {
    Container(
        modifier = modifier,
        onClick = data.onClick,
    ) {
        Text(
            text = data.value.get(),
            color = when (data.type) {
                HeaderTileState.Type.LargePrimary,
                HeaderTileState.Type.MediumPrimary,
                HeaderTileState.Type.SmallPrimary -> AppTheme.specificColorScheme.symbolPrimary
                HeaderTileState.Type.LargeSecondary,
                HeaderTileState.Type.MediumSecondary,
                HeaderTileState.Type.SmallSecondary -> AppTheme.specificColorScheme.symbolSecondary
            },
            style = when (data.type) {
                HeaderTileState.Type.LargePrimary,
                HeaderTileState.Type.LargeSecondary -> AppTheme.typography.headlineLarge
                HeaderTileState.Type.MediumPrimary,
                HeaderTileState.Type.MediumSecondary -> AppTheme.typography.headlineSmall
                HeaderTileState.Type.SmallPrimary,
                HeaderTileState.Type.SmallSecondary -> AppTheme.typography.titleMedium
            },
            modifier = Modifier.weight(1f),
        )
        when (data.action) {
            is HeaderTileState.Action.Text -> Text(
                text = data.action.text.get(),
                maxLines = 1,
                style = AppTheme.typography.labelMedium,
                color = AppTheme.specificColorScheme.symbolSecondary,
            )
            is HeaderTileState.Action.Icon -> androidx.compose.material.Icon(
                painter = data.action.icon.get(),
                contentDescription = null,
                tint = AppTheme.specificColorScheme.symbolSecondary,
            )
            null -> {}
        }
    }
}

@Composable
private fun Shimmer(
    modifier: Modifier = Modifier,
    data: HeaderTileState.Shimmer,
) {
    val type = data.type
    Container(
        modifier = modifier,
        onClick = null,
    ) {
        ShimmerTile(
            needTopLine = type == HeaderTileState.Type.SmallPrimary || type == HeaderTileState.Type.SmallSecondary,
            needMiddleLine = type == HeaderTileState.Type.LargePrimary || type == HeaderTileState.Type.LargeSecondary,
            needBottomLine = type == HeaderTileState.Type.MediumPrimary || type == HeaderTileState.Type.MediumSecondary,
            needRightIcon = false,
            needLeftIcon = false,
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
        modifier = modifier
            .fillMaxWidth()
            .defaultTileRipple(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically,
        content = content,
    )
}