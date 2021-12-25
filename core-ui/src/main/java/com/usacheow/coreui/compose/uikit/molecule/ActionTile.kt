package com.usacheow.coreui.compose.uikit.molecule

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Checkbox
import androidx.compose.material.Icon
import androidx.compose.material.Switch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.usacheow.coreui.adapter.base.WidgetState
import com.usacheow.coreui.compose.resources.AppTheme
import com.usacheow.coreui.compose.resources.Dimen
import com.usacheow.coreui.compose.tools.ImageValue
import com.usacheow.coreui.compose.tools.LazySimpleWidgetStatePreview
import com.usacheow.coreui.compose.tools.TextValue
import com.usacheow.coreui.compose.tools.doOnClick

data class ActionTileState(
    val image: ImageValue = ImageValue.Empty,
    val title: TextValue,
    val subtitle: TextValue = TextValue.Empty,
    val isChecked: Boolean = false,
    val selectorType: SelectorType = SelectorType.CheckBox,
    val clickListener: (Boolean) -> Unit = {},
) : WidgetState() {

    override val content = @Composable {
        ActionTile(image, title, subtitle, isChecked, selectorType, clickListener)
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
    image: ImageValue = ImageValue.Empty,
    title: TextValue,
    subtitle: TextValue = TextValue.Empty,
    isChecked: Boolean = false,
    selectorType: ActionTileState.SelectorType = ActionTileState.SelectorType.CheckBox,
    clickListener: (Boolean) -> Unit = {},
) {
    val (isCheckedState, onClick) = remember { mutableStateOf(isChecked) }
    val verticalAlignment = when (subtitle) {
        TextValue.Empty -> Alignment.CenterVertically
        else -> Alignment.Top
    }
    val ripplePadding = 8.dp

    Row(
        verticalAlignment = verticalAlignment,
        modifier = Modifier
            .fillMaxWidth()
            .padding(ripplePadding)
            .clip(AppTheme.shapes.medium)
            .doOnClick {
                onClick(!isCheckedState)
                clickListener(!isChecked)
            }
            .padding(Dimen.default_padding - ripplePadding),
    ) {
        image.get()?.let { LeftIcon(it) }
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = verticalAlignment,
            ) {
                PrimaryText(title)
                Selector(isCheckedState, selectorType)
            }
            if (subtitle !is TextValue.Empty) {
                SecondaryText(subtitle)
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
    LazySimpleWidgetStatePreview { generatePreviewActionTiles() }
}

@Composable
private fun generatePreviewActionTiles(): List<WidgetState> = listOf(
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