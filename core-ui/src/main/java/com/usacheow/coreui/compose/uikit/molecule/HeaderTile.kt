package com.usacheow.coreui.compose.uikit.molecule

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.usacheow.coreui.adapter.base.WidgetState
import com.usacheow.coreui.compose.resources.AppTheme
import com.usacheow.coreui.compose.tools.LazySimpleWidgetStatePreview
import com.usacheow.core.resource.compose.TextValue
import com.usacheow.coreui.compose.tools.get

data class HeaderTileState(
    val value: TextValue,
    val button: TextValue = TextValue.Empty,
    val clickListener: (() -> Unit)? = null,
    val type: Type = Type.Title,
) : WidgetState() {

    override val content = @Composable {
        HeaderTile(value, button, clickListener, type)
    }

    companion object {
        fun shimmer(type: Type = Type.Title) = ShimmerTileState(
            needTopLine = type == Type.Title,
            needBottomLine = type == Type.Subtitle,
            needMiddleLine = false,
            needRightIcon = false,
            needLeftIcon = false,
        )
    }

    enum class Type {
        Title, Subtitle
    }
}

@Composable
fun HeaderTile(
    value: TextValue,
    button: TextValue = TextValue.Empty,
    clickListener: (() -> Unit)? = null,
    type: HeaderTileState.Type = HeaderTileState.Type.Title,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp),
    ) {
        Text(
            text = value.get(),
            color = AppTheme.commonColors.symbolPrimary,
            style = when (type) {
                HeaderTileState.Type.Title -> AppTheme.typography.displayLarge
                HeaderTileState.Type.Subtitle -> AppTheme.typography.titleLarge
            },
            maxLines = 1,
            modifier = Modifier
                .weight(1f)
                .padding(8.dp),
        )
        if (button != TextValue.Empty) {
            ClickableText(
                text = button.get(),
                maxLines = 1,
                onClick = { clickListener?.invoke() },
                style = AppTheme.typography.labelMedium.copy(color = LocalContentColor.current),
                modifier = Modifier
                    .padding(8.dp)
                    .align(Alignment.CenterVertically),
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
private fun HeaderTilePreview() {
    LazySimpleWidgetStatePreview { generatePreviewHeaderTiles() }
}

@Composable
private fun generatePreviewHeaderTiles(): List<WidgetState> = listOf(
    HeaderTileState.shimmer(HeaderTileState.Type.Title),
    HeaderTileState(
        value = TextValue.Simple("Title"),
        type = HeaderTileState.Type.Title,
    ),
    HeaderTileState(
        value = TextValue.Simple("Title with button"),
        type = HeaderTileState.Type.Title,
        button = TextValue.Simple("button"),
        clickListener = {},
    ),
    HeaderTileState.shimmer(HeaderTileState.Type.Subtitle),
    HeaderTileState(
        value = TextValue.Simple("Subtitle"),
        type = HeaderTileState.Type.Subtitle,
    ),
    HeaderTileState(
        value = TextValue.Simple("Subtitle with button"),
        type = HeaderTileState.Type.Subtitle,
        button = TextValue.Simple("button"),
        clickListener = {},
    ),
)