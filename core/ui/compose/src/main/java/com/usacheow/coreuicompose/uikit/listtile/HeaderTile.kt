package com.usacheow.coreuicompose.uikit.listtile

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.usacheow.corecommon.container.IconValue
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.TileState
import com.usacheow.coreuicompose.tools.defaultTileRipple
import com.usacheow.coreuicompose.tools.get
import com.usacheow.coreuicompose.uikit.other.ShimmerTileConfig
import com.usacheow.coreuicompose.uikit.other.ShimmerTileLine
import com.usacheow.coreuitheme.compose.AppTheme

sealed class HeaderTileState : TileState {

    data class Data(
        val value: TextValue,
        val action: Action? = null,
        val type: Type = Type.Medium,
    ) : HeaderTileState()

    data class Shimmer(
        val type: Type = Type.Medium,
    ) : HeaderTileState()

    @Composable
    override fun Content(modifier: Modifier) {
        HeaderTile(modifier, this)
    }

    companion object {
        fun large(value: TextValue) = Data(value = value, type = Type.Large)
        fun medium(value: TextValue) = Data(value = value, type = Type.Medium)
        fun small(value: TextValue) = Data(value = value, type = Type.Small)
    }

    enum class Type {
        Large, Medium, Small,
    }

    sealed class Action {

        data class Text(
            val text: TextValue,
            val onClick: () -> Unit,
        ) : Action()

        data class Icon(
            val icon: IconValue,
            val onClick: () -> Unit,
        ) : Action()
    }
}

object HeaderTileConfig {

    @Composable
    fun headerStyle(type: HeaderTileState.Type) = when (type) {
        HeaderTileState.Type.Large -> AppTheme.specificTypography.headlineLarge
        HeaderTileState.Type.Medium -> AppTheme.specificTypography.headlineSmall
        HeaderTileState.Type.Small -> AppTheme.specificTypography.titleMedium
    }

    @Composable
    fun headerColor() = AppTheme.specificColorScheme.symbolPrimary

    @Composable
    fun actionColor() = AppTheme.specificColorScheme.symbolSecondary

    @Composable
    fun shimmerWidth(type: HeaderTileState.Type) = when (type) {
        HeaderTileState.Type.Small -> ShimmerTileConfig.WidthSmall
        HeaderTileState.Type.Large -> ShimmerTileConfig.WidthLarge
        HeaderTileState.Type.Medium -> ShimmerTileConfig.WidthMedium
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
    Container(modifier = modifier) {
        Text(
            text = data.value.get(),
            color = HeaderTileConfig.headerColor(),
            style = HeaderTileConfig.headerStyle(data.type),
            modifier = Modifier.weight(1f),
        )
        when (data.action) {
            is HeaderTileState.Action.Text -> Text(
                text = data.action.text.get(),
                maxLines = 1,
                style = AppTheme.specificTypography.labelMedium,
                color = HeaderTileConfig.actionColor(),
                modifier = Modifier.defaultTileRipple(onClick = data.action.onClick),
            )
            is HeaderTileState.Action.Icon -> Icon(
                painter = data.action.icon.get(),
                contentDescription = null,
                tint = HeaderTileConfig.actionColor(),
                modifier = Modifier.defaultTileRipple(onClick = data.action.onClick),
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
    Container(modifier = modifier) {
        ShimmerTileLine(
            width = HeaderTileConfig.shimmerWidth(data.type),
            height = HeaderTileConfig.headerStyle(data.type).lineHeight.value.dp,
        )
    }
}

@Composable
private fun Container(
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        content = content,
    )
}