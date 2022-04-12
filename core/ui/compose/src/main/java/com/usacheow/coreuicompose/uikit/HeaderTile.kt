package com.usacheow.coreuicompose.uikit

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.usacheow.coreuicompose.tools.WidgetState
import com.usacheow.coreuitheme.compose.AppTheme
import com.usacheow.corecommon.container.TextValue
import com.usacheow.coreuicompose.tools.ShimmerState
import com.usacheow.coreuicompose.tools.SimplePreview
import com.usacheow.coreuicompose.tools.doOnClick
import com.usacheow.coreuicompose.tools.get

data class HeaderTileState(
    val value: TextValue,
    val button: TextValue? = null,
    val clickListener: (() -> Unit)? = null,
    val type: Type = Type.MediumPrimary,
) : WidgetState {

    @Composable
    override fun Content(modifier: Modifier) {
        HeaderTile(modifier, this)
    }

    companion object {
        fun shimmer(type: Type = Type.MediumPrimary) = ShimmerState {
            ShimmerTile(
                modifier = it,
                needTopLine = type == Type.SmallPrimary || type == Type.SmallSecondary,
                needMiddleLine = type == Type.LargePrimary || type == Type.LargeSecondary,
                needBottomLine = type == Type.MediumPrimary || type == Type.MediumSecondary,
                needRightIcon = false,
                needLeftIcon = false,
            )
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
}

@Composable
fun HeaderTile(
    modifier: Modifier = Modifier,
    data: HeaderTileState,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = data.value.get(),
            color = when (data.type) {
                HeaderTileState.Type.LargePrimary,
                HeaderTileState.Type.MediumPrimary,
                HeaderTileState.Type.SmallPrimary -> AppTheme.commonColors.symbolPrimary
                HeaderTileState.Type.LargeSecondary,
                HeaderTileState.Type.MediumSecondary,
                HeaderTileState.Type.SmallSecondary -> AppTheme.commonColors.symbolSecondary
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
        data.button?.get()?.let {
            Text(
                text = it,
                maxLines = 1,
                style = AppTheme.typography.labelMedium,
                color = AppTheme.commonColors.symbolSecondary,
                modifier = Modifier
                    .clip(AppTheme.shapes.extraSmall)
                    .doOnClick(onClick = data.clickListener)
                    .padding(8.dp),
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun Preview() {
    SimplePreview {
        HeaderTileState(
            value = TextValue.Simple("Title with button"),
            type = HeaderTileState.Type.MediumPrimary,
            button = TextValue.Simple("button"),
            clickListener = {},
        ).Content(Modifier)
    }
}