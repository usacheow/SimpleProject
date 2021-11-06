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
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.Switch
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.usacheow.coreui.adapter.base.WidgetState
import com.usacheow.coreui.compose.resources.CommonDimens
import com.usacheow.coreui.compose.resources.secondaryTextAlpha
import com.usacheow.coreui.compose.tools.ImageValue
import com.usacheow.coreui.compose.tools.LazySimpleWidgetStatePreview
import com.usacheow.coreui.compose.tools.TextValue

data class ActionTileItem(
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
        fun shimmer() = ShimmerTileItem(
            needBottomLine = false,
            needRightIcon = false)
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
    selectorType: ActionTileItem.SelectorType = ActionTileItem.SelectorType.CheckBox,
    clickListener: (Boolean) -> Unit = {},
) {
    val (isCheckedState, onClick) = remember { mutableStateOf(isChecked) }
    val verticalAlignment = when (subtitle) {
        TextValue.Empty -> Alignment.CenterVertically
        else -> Alignment.Top
    }

    Surface(
        color = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        indication = rememberRipple(),
        onClick = {
            onClick(!isCheckedState)
            clickListener(!isChecked)
        },
    ) {
        Row(
            verticalAlignment = verticalAlignment,
            modifier = Modifier
                .padding(CommonDimens.default_screen_margin)
                .fillMaxWidth(),
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
}

@Composable
private fun LeftIcon(icon: Painter) {
    Icon(
        painter = icon,
        contentDescription = "Item icon",
        modifier = Modifier
            .width(36.dp)
            .padding(end = CommonDimens.default_screen_margin))
}

@Composable
private fun RowScope.PrimaryText(value: TextValue) {
    Text(
        text = value.get(),
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier.weight(1f))
}

@Composable
private fun Selector(isChecked: Boolean, selectorType: ActionTileItem.SelectorType) {
    when (selectorType) {
        ActionTileItem.SelectorType.Switch -> Switch(
            checked = isChecked,
            onCheckedChange = null,
            modifier = Modifier.height(24.dp))

        ActionTileItem.SelectorType.CheckBox -> Checkbox(
            checked = isChecked,
            onCheckedChange = null,
            modifier = Modifier.size(
                height = 24.dp,
                width = 32.dp))
    }
}

@Composable
private fun SecondaryText(value: TextValue) {
    CompositionLocalProvider(LocalContentAlpha provides secondaryTextAlpha) {
        Text(
            text = value.get(),
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 4.dp))
    }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ActionTilePreview() {
    LazySimpleWidgetStatePreview { generatePreviewActionTiles() }
}

@Composable
fun generatePreviewActionTiles(): List<WidgetState> = listOf(
    ActionTileItem.shimmer(),
    ActionTileItem(
        image = ImageValue.Vector(Icons.Default.VerifiedUser),
        title = TextValue.Simple("Title"),
        subtitle = TextValue.Empty,
        isChecked = false,
        selectorType = ActionTileItem.SelectorType.CheckBox,
        clickListener = {}),
    ActionTileItem(
        image = ImageValue.Vector(Icons.Default.VerifiedUser),
        title = TextValue.Simple("Title"),
        subtitle = TextValue.Simple("Subtitle"),
        isChecked = false,
        selectorType = ActionTileItem.SelectorType.CheckBox,
        clickListener = {}),
    ActionTileItem(
        image = ImageValue.Vector(Icons.Default.VerifiedUser),
        title = TextValue.Simple("Title"),
        subtitle = TextValue.Simple("Subtitle"),
        isChecked = true,
        selectorType = ActionTileItem.SelectorType.Switch,
        clickListener = {}),
)