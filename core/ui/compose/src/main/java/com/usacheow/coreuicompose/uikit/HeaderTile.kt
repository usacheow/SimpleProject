package com.usacheow.coreuicompose.uikit

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.usacheow.corecommon.container.IconValue
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.ShimmerState
import com.usacheow.coreuicompose.tools.WidgetState
import com.usacheow.coreuicompose.tools.defaultTileRipple
import com.usacheow.coreuicompose.tools.get
import com.usacheow.coreuicompose.uikit.status.ShimmerTile
import com.usacheow.coreuitheme.compose.AppTheme
import com.usacheow.coreuitheme.compose.PreviewAppTheme

data class HeaderTileState(
    val value: TextValue,
    val button: TextValue? = null,
    val action: Action? = null,
    val clickListener: (() -> Unit)? = null,
    val type: Type = Type.MediumPrimary,
) : WidgetState {

    @Composable
    override fun Content(modifier: Modifier) {
        HeaderTile(modifier, this)
    }

    companion object {
        fun shimmer(type: Type = Type.MediumPrimary) = ShimmerState {
            HeaderTileContainer(
                modifier = it,
                clickListener = null,
            ) {
                ShimmerTile(
                    needTopLine = type == Type.SmallPrimary || type == Type.SmallSecondary,
                    needMiddleLine = type == Type.LargePrimary || type == Type.LargeSecondary,
                    needBottomLine = type == Type.MediumPrimary || type == Type.MediumSecondary,
                    needRightIcon = false,
                    needLeftIcon = false,
                )
            }
        }

        fun largePrimary(value: TextValue) = HeaderTileState(value = value, type = Type.LargePrimary)
        fun largeSecondary(value: TextValue) = HeaderTileState(value = value, type = Type.LargeSecondary)
        fun mediumPrimary(value: TextValue) = HeaderTileState(value = value, type = Type.MediumPrimary)
        fun mediumSecondary(value: TextValue) = HeaderTileState(value = value, type = Type.MediumSecondary)
        fun smallPrimary(value: TextValue) = HeaderTileState(value = value, type = Type.SmallPrimary)
        fun smallSecondary(value: TextValue) = HeaderTileState(value = value, type = Type.SmallSecondary)
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
    HeaderTileContainer(
        modifier = modifier,
        clickListener = data.clickListener,
    ) {
        Text(
            text = data.value.get(),
            color = when (data.type) {
                HeaderTileState.Type.LargePrimary,
                HeaderTileState.Type.MediumPrimary,
                HeaderTileState.Type.SmallPrimary,
                -> AppTheme.specificColorScheme.symbolPrimary
                HeaderTileState.Type.LargeSecondary,
                HeaderTileState.Type.MediumSecondary,
                HeaderTileState.Type.SmallSecondary,
                -> AppTheme.specificColorScheme.symbolSecondary
            },
            style = when (data.type) {
                HeaderTileState.Type.LargePrimary,
                HeaderTileState.Type.LargeSecondary,
                -> AppTheme.typography.headlineLarge
                HeaderTileState.Type.MediumPrimary,
                HeaderTileState.Type.MediumSecondary,
                -> AppTheme.typography.headlineSmall
                HeaderTileState.Type.SmallPrimary,
                HeaderTileState.Type.SmallSecondary,
                -> AppTheme.typography.titleMedium
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
private fun HeaderTileContainer(
    modifier: Modifier = Modifier,
    clickListener: (() -> Unit)?,
    content: @Composable RowScope.() -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .defaultTileRipple(onClick = clickListener),
        verticalAlignment = Alignment.CenterVertically,
        content = content,
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    PreviewAppTheme {
        HeaderTileState(
            value = TextValue.Simple("Title with button"),
            type = HeaderTileState.Type.MediumPrimary,
            action = HeaderTileState.Action.Text(TextValue.Simple("action")),
            clickListener = {},
        ).Content(Modifier)
    }
}