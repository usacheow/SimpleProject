package com.usacheow.coreuicompose.uikit

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
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

data class ActionTileState(
    val image: ImageValue = ImageValue.Empty,
    val title: TextValue,
    val subtitle: TextValue = TextValue.Empty,
    val isChecked: Boolean = false,
    val selectorType: SelectorType = SelectorType.CheckBox,
    val clickListener: (Boolean) -> Unit = {},
) : WidgetState {

    @Composable
    override fun Content(modifier: Modifier) {
        ActionTile(modifier, this)
    }

    companion object {
        fun shimmer() = ShimmerTileState(
            needBottomLine = false,
            needRightIcon = false,
        )
    }

    enum class SelectorType {
        CheckBox, Switch
    }
}

@Composable
fun ActionTile(
    modifier: Modifier = Modifier,
    data: ActionTileState,
) {
    val verticalAlignment = when (data.subtitle) {
        TextValue.Empty -> Alignment.CenterVertically
        else -> Alignment.Top
    }
    val ripplePadding = 8.dp

    Row(
        verticalAlignment = verticalAlignment,
        modifier = modifier
            .fillMaxWidth()
            .padding(ripplePadding)
            .clip(AppTheme.shapes.medium)
            .doOnClick {
                data.clickListener(!data.isChecked)
            }
            .padding(Dimen.default_padding - ripplePadding),
    ) {
        data.image.get()?.let { LeftIcon(it) }
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = verticalAlignment,
            ) {
                PrimaryText(data.title)
                Selector(data.isChecked, data.selectorType)
            }
            if (data.subtitle !is TextValue.Empty) {
                SecondaryText(data.subtitle)
            }
        }
    }
}

@Composable
private fun LeftIcon(icon: Painter) {
    Icon(
        painter = icon,
        tint = AppTheme.commonColors.symbolSecondary,
        contentDescription = "Item icon",
        modifier = Modifier
            .width(36.dp)
            .padding(end = Dimen.default_padding),
    )
}

@Composable
private fun RowScope.PrimaryText(value: TextValue) {
    Text(
        text = value.get(),
        color = AppTheme.commonColors.symbolPrimary,
        style = AppTheme.typography.bodyLarge,
        modifier = Modifier.weight(1f),
    )
}

@Composable
private fun Selector(isChecked: Boolean, selectorType: ActionTileState.SelectorType) {
    when (selectorType) {
        ActionTileState.SelectorType.Switch -> Switch(
            checked = isChecked,
            onCheckedChange = null,
            modifier = Modifier.height(24.dp),
        )

        ActionTileState.SelectorType.CheckBox -> Checkbox(
            checked = isChecked,
            onCheckedChange = null,
            modifier = Modifier.size(height = 24.dp, width = 32.dp),
        )
    }
}

@Composable
private fun SecondaryText(value: TextValue) {
    Text(
        text = value.get(),
        color = AppTheme.commonColors.symbolSecondary,
        style = AppTheme.typography.bodyMedium,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 4.dp),
    )
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ActionTilePreview() {
    SimplePreview {
        LazyColumn {
            items(previewActionTiles()) {
                it.Content()
            }
        }
    }
}

private fun previewActionTiles(): List<WidgetState> = listOf(
    ActionTileState.shimmer(),
    ActionTileState(
        image = ImageValue.Vector(Icons.Default.VerifiedUser),
        title = TextValue.Simple("Title"),
        subtitle = TextValue.Empty,
        isChecked = false,
        selectorType = ActionTileState.SelectorType.CheckBox,
        clickListener = {},
    ),
    ActionTileState(
        image = ImageValue.Vector(Icons.Default.VerifiedUser),
        title = TextValue.Simple("Title"),
        subtitle = TextValue.Simple("Subtitle"),
        isChecked = false,
        selectorType = ActionTileState.SelectorType.CheckBox,
        clickListener = {},
    ),
    ActionTileState(
        image = ImageValue.Vector(Icons.Default.VerifiedUser),
        title = TextValue.Simple("Title"),
        subtitle = TextValue.Simple("Subtitle"),
        isChecked = true,
        selectorType = ActionTileState.SelectorType.Switch,
        clickListener = {},
    ),
)